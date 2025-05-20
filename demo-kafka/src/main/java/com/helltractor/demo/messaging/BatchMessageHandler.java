package com.helltractor.demo.messaging;

import com.helltractor.demo.message.AbstractMessage;

import java.util.List;

@FunctionalInterface
public interface BatchMessageHandler<T extends AbstractMessage> {

    void processMessages(List<T> messages);
}
