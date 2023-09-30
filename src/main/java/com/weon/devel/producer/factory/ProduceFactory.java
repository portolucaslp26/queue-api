package com.weon.devel.producer.factory;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.weon.devel.producer.Producer;
import com.weon.devel.producer.chat.ChatProducer;
import com.weon.devel.producer.email.EmailProducer;
import com.weon.devel.producer.voice.VoiceProducer;

@Component
public class ProduceFactory {
    private Map<String, Producer> producers = new HashMap<>();
    
    public ProduceFactory() {
    	producers.put("voice", new VoiceProducer());
    	producers.put("email", new EmailProducer());
    	producers.put("chat", new ChatProducer());
    }
    
    public Producer createProducer(String type) {
    	return producers.getOrDefault(type, null);
    }

}	
