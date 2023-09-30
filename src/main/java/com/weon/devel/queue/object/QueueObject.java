package com.weon.devel.queue.object;

import java.time.LocalDateTime;
import java.util.UUID;

public class QueueObject {
	
    private UUID id;
    private LocalDateTime dateTime;
    private String source;
    private String destination;

    public QueueObject(UUID id, LocalDateTime dateTime, String source, String destination) {
        this.id = UUID.randomUUID();
        this.dateTime = dateTime;
        this.source = source;
        this.destination = destination;
    }
    
    public QueueObject() {
    	
    }
    
    public void setId(UUID id) {
    	this.id = UUID.randomUUID();
    }

    public UUID getId() {
        return id;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }
    
    public void setDateTime(LocalDateTime dateTime) {
    	this.dateTime = LocalDateTime.now();
    }

    public String getSource() {
        return source;
    }
    
    public void setSource(String source) {
    	this.source = source;
    }

    public String getDestination() {
        return destination;
    }
    
    public void setDestination(String destination) {
    	this.destination = destination;
    }
}


