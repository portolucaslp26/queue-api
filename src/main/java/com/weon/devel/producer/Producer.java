package com.weon.devel.producer;

import java.time.LocalDateTime;
import java.util.UUID;

import com.weon.devel.producer.chat.ChatChannel;
import com.weon.devel.producer.email.EmailChannel;
import com.weon.devel.producer.voice.VoiceChannel;

public interface Producer {
	default VoiceChannel createVoiceChannel(UUID id, LocalDateTime dateTime, String sourcePhone, String destinationPhone) {
        return new VoiceChannel(id, dateTime, sourcePhone, destinationPhone);
    }

    default EmailChannel createEmailChannel(UUID id, LocalDateTime dateTime, String sourceEmail, String destinationEmail) {
        return new EmailChannel(id, dateTime, sourceEmail, destinationEmail);
    }

    default ChatChannel createChatChannel(UUID id, LocalDateTime dateTime, String sourceUser, String destinationUser) {
        return new ChatChannel(id, dateTime, sourceUser, destinationUser);
    }
}
