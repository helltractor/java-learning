package com.helltractor.demo.constant;

import io.grpc.Context;
import io.grpc.Metadata;

import static io.grpc.Metadata.ASCII_STRING_MARSHALLER;

public class JwtConstant {
    
    public static final String JWT_SIGNING_KEY = "L8hHXsaQOUjk5rg7XPGv4eL36anlCrkMz8CJ0i/8E/0=";
    
    public static final String BEARER_TYPE = "Bearer";
    
    public static final Context.Key<String> CLIENT_ID_CONTEXT_KEY = Context.key("clientId");
    
    public static final Metadata.Key<String> AUTHORIZATION_METADATA_KEY = Metadata.Key.of("Authorization", ASCII_STRING_MARSHALLER);
    
    public static final long JWT_TTL = 3600000;
    
}