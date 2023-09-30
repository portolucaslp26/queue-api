package com.weon.devel.producer.email;

import java.time.LocalDateTime;
import java.util.UUID;

import com.weon.devel.producer.Producer;

public class EmailProducer implements Producer {
    @Override
    public EmailChannel createEmailChannel(UUID id, LocalDateTime dateTime, String sourceEmail, String destinationEmail) {
        return new EmailChannel(id, dateTime, sourceEmail, destinationEmail);
    }
}
