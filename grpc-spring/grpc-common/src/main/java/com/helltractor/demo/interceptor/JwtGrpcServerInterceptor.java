package com.helltractor.demo.interceptor;

import com.helltractor.demo.constant.JwtConstant;
import com.helltractor.demo.util.JwtUtil;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;

public class JwtGrpcServerInterceptor implements ServerInterceptor {
    
    @Override
    public <ReqT, RespT> ServerCall.Listener<ReqT> interceptCall(ServerCall<ReqT, RespT> serverCall,
                                                                 Metadata metadata, ServerCallHandler<ReqT, RespT> serverCallHandler) {
        String value = metadata.get(JwtConstant.AUTHORIZATION_METADATA_KEY);
        Status status = Status.OK;
        if (value == null) {
            status = Status.UNAUTHENTICATED.withDescription("Authorization token is missing");
        } else if (!value.startsWith(JwtConstant.BEARER_TYPE)) {
            status = Status.UNAUTHENTICATED.withDescription("Unknown authorization type");
        } else {
            Jws<Claims> claims = null;
            // remove authorization type prefix
            String token = value.substring(JwtConstant.BEARER_TYPE.length()).trim();
            try {
                // verify token signature and parse claims
                claims = JwtUtil.parseJWT(token);
            } catch (JwtException e) {
                status = Status.UNAUTHENTICATED.withDescription(e.getMessage()).withCause(e);
            }
            if (claims != null) {
                // set client id into current context
                Context ctx = Context.current()
                        .withValue(JwtConstant.CLIENT_ID_CONTEXT_KEY, claims.getPayload().getSubject());
                return Contexts.interceptCall(ctx, serverCall, metadata, serverCallHandler);
            }
        }
        
        serverCall.close(status, new Metadata());
        return new ServerCall.Listener<ReqT>() {
        };
    }
    
}
