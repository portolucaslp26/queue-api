package com.weon.devel;

import com.weon.devel.channel.Channel;
import com.weon.devel.producer.Producer;
import com.weon.devel.producer.email.EmailChannel;
import com.weon.devel.producer.factory.ProduceFactory;
import com.weon.devel.queue.controller.QueueController;
import com.weon.devel.queue.object.QueueObject;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.weon.devel.queue.service.QueueService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


import java.time.LocalDateTime;
import java.util.UUID;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class DevelEnqueueTests {
	private static final Logger log = LoggerFactory.getLogger(EmailChannel.class);

	@Mock
	private ProduceFactory producerFactory;

	@Mock
	private QueueService queueService;

	@InjectMocks
	private QueueController queueController;

	@Test
	void testEnqueueWithValidType() {
		// Arrange
		String type = "email";
		QueueObject item = new QueueObject();
		when(producerFactory.createProducer(type)).thenReturn(mock(Producer.class));
		item.setId(UUID.randomUUID());
		item.setDateTime(LocalDateTime.now());
		item.setDestination("test@devel.com");
		item.setSource("test2@devel.com");
		Producer producer = producerFactory.createProducer(type);
		when(producer.createEmailChannel(
				any(UUID.class),
				any(LocalDateTime.class),
				eq("test2@devel.com"),
				eq("test@devel.com")
		)).thenReturn(mock(EmailChannel.class));
		// Act
		ResponseEntity<String> response = queueController.enqueue(type, item);

		// Assert
		assertEquals(HttpStatus.CREATED, response.getStatusCode());
		assertEquals("Item queued successfully.", response.getBody());
		verify(queueService, times(1)).enqueue(any(Channel.class));
	}

	@Test
	void testEnqueueWithInvalidType() {
		// Arrange
		String type = "invalidType";
		QueueObject item = new QueueObject();

		// Act
		ResponseEntity<String> response = queueController.enqueue(type, item);

		// Assert
		assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
		assertEquals("Invalid channel type.", response.getBody());
		verify(queueService, never()).enqueue(any(Channel.class));
	}
}
