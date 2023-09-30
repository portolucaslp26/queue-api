package com.weon.devel.queue.service;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

import org.springframework.stereotype.Service;

import com.weon.devel.channel.Channel;

@Service
public class QueueService {

    private Queue<Channel> queue = new ConcurrentLinkedQueue<>();

    public void enqueue(Channel channel) {
        queue.offer(channel);
    }

    public Channel dequeue() {
        return queue.poll();
    }

    public boolean isEmpty() {
        return queue.isEmpty();
    }

    public int size() {
        return queue.size();
    }
    
    public List<Channel> getAllItems() {
        return new LinkedList<>(queue);
    }
}



