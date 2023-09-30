package com.weon.devel.producer.voice;

import java.time.LocalDateTime;
import java.util.UUID;

import com.weon.devel.producer.Producer;

public class VoiceProducer implements Producer {
    @Override
    public VoiceChannel createVoiceChannel(UUID id, LocalDateTime dateTime, String sourcePhone, String destinationPhone) {
        return new VoiceChannel(id, dateTime, sourcePhone, destinationPhone);
    }
}
