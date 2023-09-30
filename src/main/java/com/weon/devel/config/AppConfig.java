package com.weon.devel.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.weon.devel.consumer.thread.ConsumerThread;
import com.weon.devel.queue.service.QueueService;

@Configuration
public class AppConfig {
    @Bean
    @ConditionalOnMissingBean
    public ConsumerThread consumerThread(QueueService queueService) {
        return new ConsumerThread(queueService);
    }
}
