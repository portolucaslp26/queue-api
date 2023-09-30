package com.weon.devel.producer.chat;

import java.time.LocalDateTime;
import java.util.UUID;

import com.weon.devel.producer.Producer;

public class ChatProducer implements Producer {
    @Override
    public ChatChannel createChatChannel(UUID id, LocalDateTime dateTime, String sourceUser, String destinationUser) {
        return new ChatChannel(id, dateTime, sourceUser, destinationUser);
    }
}
