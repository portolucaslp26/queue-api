package com.weon.devel.producer.chat;

import java.time.LocalDateTime;
import java.util.UUID;

import javax.validation.constraints.NotBlank;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.weon.devel.channel.Channel;

public class ChatChannel implements Channel {
	private UUID id;
	
	@NotBlank
    private String sourceUser;
	
	@NotBlank
    private String destinationUser;
    private LocalDateTime dateTime;

    private static final Logger logger = LoggerFactory.getLogger(ChatChannel.class);

    public ChatChannel(UUID id, LocalDateTime dateTime, String sourceUser, String destinationUser) {
        this.id =  UUID.randomUUID();
        this.sourceUser = sourceUser;
        this.destinationUser = destinationUser;
        this.dateTime = LocalDateTime.now();
    }
    
    @Override
    public void executeCommand() {
    	logger.info("Chat message sent from " + sourceUser + " to " + destinationUser + " at " + dateTime);
    }
    
    public UUID getId() {
    	return this.id;
    }
    
    public LocalDateTime getDateTime() {
    	return this.dateTime;
    }

    public String getSourceUser() {
        return sourceUser;
    }

    public void setSourceUser(String sourceUser) {
        this.sourceUser = sourceUser;
    }

    public String getDestinationUser() {
        return destinationUser;
    }

    public void setDestinationUser(String destinationUser) {
        this.destinationUser = destinationUser;
    }
}
