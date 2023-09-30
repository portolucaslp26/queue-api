package com.weon.devel.producer.voice;

import java.time.LocalDateTime;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.weon.devel.channel.Channel;
import com.weon.devel.producer.voice.validator.VoiceValidator;

public class VoiceChannel implements Channel{
	private UUID id;
    private String sourcePhone;
    private String destinationPhone;
    private LocalDateTime dateTime;

    private static final Logger logger = LoggerFactory.getLogger(VoiceChannel.class);
    
    private static final VoiceValidator validator = new VoiceValidator();

    public VoiceChannel(UUID id, LocalDateTime dateTime, String sourcePhone, String destinationPhone) {
        this.id = UUID.randomUUID();
        setSourcePhone(sourcePhone);
        setDestinationPhone(destinationPhone);
        this.dateTime = LocalDateTime.now();
    }
    
    @Override
    public void executeCommand() {
        logger.info("Call made between " + sourcePhone + " and " + destinationPhone + " at " + dateTime);
    }
    
    public UUID getId() {
    	return this.id;
    }
    
    public LocalDateTime getDateTime() {
    	return this.dateTime;
    }
    
    public String getSourcePhone() {
        return sourcePhone;
    }

    public void setSourcePhone(String sourcePhone) {
        validator.validatePhone(sourcePhone);
        this.sourcePhone = sourcePhone;
    }

    public String getDestinationPhone() {
        return destinationPhone;
    }
    public void setDestinationPhone(String destinationPhone) {
        validator.validatePhone(destinationPhone);
        this.destinationPhone = destinationPhone;
    }
}