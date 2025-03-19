package com.helltractor.demo.pool;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Queue;
import java.util.function.Supplier;

public class LazyPooledConnectionProxy extends AbstractConnectionProxy {
    
    Connection target;
    Queue<LazyPooledConnectionProxy> idleQueue;
    private Supplier<Connection> supplier;
    
    public LazyPooledConnectionProxy(Queue<LazyPooledConnectionProxy> idleQueue, Supplier<Connection> supplier) {
        this.idleQueue = idleQueue;
        this.supplier = supplier;
    }
    
    @Override
    protected Connection getRealConnection() {
        if (target == null) {
            target = supplier.get();
        }
        return target;
    }
    
    @Override
    public void close() throws SQLException {
        if (target != null) {
            System.out.println("Fake close and released to idle queue for future reuse: " + target);
            idleQueue.offer(this);
        }
    }
    
}