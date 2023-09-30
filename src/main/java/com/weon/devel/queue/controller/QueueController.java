package com.weon.devel.queue.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.weon.devel.channel.Channel;
import com.weon.devel.consumer.thread.ConsumerThread;
import com.weon.devel.producer.Producer;
import com.weon.devel.producer.factory.ProduceFactory;
import com.weon.devel.queue.object.QueueObject;
import com.weon.devel.queue.service.QueueService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;

@RestController
public class QueueController {
	
	@Autowired
	private ConsumerThread consumerThread;
	
    @Autowired
    private QueueService queueService;
    
    @Autowired
    private ProduceFactory produceFactory;

    @PostMapping("/enqueue/{type}")
    // The @Operation annotation is for manually configuring documentation on swagger.io
    @Operation(summary = "Enqueue a new item", description = "Enqueue a new item. The request body depends on the type parameter: \n\n" +
            "* For type=email: {\"source\": \"source@example.com\", \"destination\": \"destination@example.com\"}\n" +
            "* For type=chat: {\"source\": \"Sender\", \"destination\": \"Reciever\"}\n" +
            "* For type=voice: {\"source\": \"1234567890\", \"destination\": \"0987654321\"}", 
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(content = @Content(mediaType = "application/json", examples = {
                @ExampleObject(name = "email", value = "{\"source\": \"source@example.com\", \"destination\": \"destination@example.com\"}"),
                @ExampleObject(name = "chat", value = "{\"source\": \"Sender\", \"destination\": \"Reciever\"}"),
                @ExampleObject(name = "voice", value = "{\"source\": \"1234567890\", \"destination\": \"0987654321\"}")
            })))
    public ResponseEntity<String> enqueue(@PathVariable String type, @RequestBody QueueObject item) {
        try {
            Producer producer = produceFactory.createProducer(type);
            Channel channel = null;
            
            // Create a channel based in type parameter
            if (type.equalsIgnoreCase("voice")) {
            	channel = producer.createVoiceChannel(item.getId(), LocalDateTime.now(), item.getSource(), item.getDestination());
            } else if (type.equalsIgnoreCase("email")) {
            	channel = producer.createEmailChannel(item.getId(), LocalDateTime.now(), item.getSource(), item.getDestination());
            } else if (type.equalsIgnoreCase("chat")) {
            	channel = producer.createChatChannel(item.getId(), LocalDateTime.now(), item.getSource(), item.getDestination());
            } else {
                return ResponseEntity.badRequest().body("Tipo de canal inválido.");
            }
            
            channel.executeCommand();

            queueService.enqueue(channel);

            return ResponseEntity.status(HttpStatus.CREATED).body("Item queued successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error enqueuing item: " + e.getMessage());
        }
    }
    

    @GetMapping("/dequeue")
    public String dequeue() {
    	ConsumerThread consumer = consumerThread;
    	consumer.run();
    	return "Item consimido com sucesso";
    }

    @GetMapping("/isEmpty")
    public String isEmpty() {
        boolean empty = queueService.isEmpty();
        return "Queue is empty: " + empty;
    }
    
    @GetMapping("/size")
    public int size() {
        return queueService.size();
    }
    
    @GetMapping("/allItems")
    public List<Channel> getAllItems() {
        return queueService.getAllItems();
    }
}

