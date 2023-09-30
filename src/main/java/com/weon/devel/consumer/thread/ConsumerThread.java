package com.weon.devel.consumer.thread;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.weon.devel.channel.Channel;
import com.weon.devel.queue.service.QueueService;

public class ConsumerThread {
	
    private static final Logger logger = LoggerFactory.getLogger(ConsumerThread.class);
    private final QueueService queueService;

    public ConsumerThread(QueueService queueService) {
        this.queueService = queueService;
    }

    
    public void run() {
        if (!queueService.isEmpty()) {
            Channel channel = queueService.dequeue();
            logger.info("Consumed item: " + channel.getId());
        }
        
    }
}
