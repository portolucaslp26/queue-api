package com.weon.devel.producer.email;

import java.time.LocalDateTime;
import java.util.UUID;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.weon.devel.channel.Channel;
import com.weon.devel.producer.email.validator.EmailValidator;

public class EmailChannel implements Channel {
	private UUID id;
	
	@Email
	@NotBlank
    private String sourceEmail;
	
	@Email
	@NotBlank
    private String destinationEmail;
	
    private LocalDateTime dateTime;

    private static final Logger logger = LoggerFactory.getLogger(EmailChannel.class);
    
    private static final EmailValidator validator = new EmailValidator();
    
    public EmailChannel(UUID id, LocalDateTime dateTime, String sourceEmail, String destinationEmail) {
        this.id =  UUID.randomUUID();
        setDestinationEmail(sourceEmail);
        setDestinationEmail(destinationEmail);
        this.dateTime = LocalDateTime.now();
    }
    
    @Override
    public void executeCommand() {
    	logger.info("Email sent from " + sourceEmail + " to " + destinationEmail + " at " + dateTime);
    }
    
    public UUID getId() {
    	return this.id;
    }
    
    public LocalDateTime getDateTime() {
    	return this.dateTime;
    }

    public String getSourceEmail() {
        return sourceEmail;
    }

    public void setSourceEmail(String sourceEmail) {
        this.sourceEmail = sourceEmail;
    }

    public String getDestinationEmail() {
    	validator.validateEmail(destinationEmail);
        return destinationEmail;
    }

    public void setDestinationEmail(String destinationEmail) {
    	validator.validateEmail(destinationEmail);
        this.destinationEmail = destinationEmail;
    }
}
