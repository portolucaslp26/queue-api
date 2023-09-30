package com.weon.devel.producer.controller;

import java.util.Queue;
import java.util.Timer;
import java.util.TimerTask;

import com.weon.devel.channel.Channel;

public class ProducerController {
    private Timer timer;
    private Queue<Channel> queue;

    public ProducerController(Queue<Channel> queue) {
        this.queue = queue;
        this.timer = new Timer();
    }

    public void startProducing(int periodInMillis, int durationInMillis) {
        TimerTask producerTask = new TimerTask() {
            @Override
            public void run() {
            	
            }
        };

        timer.scheduleAtFixedRate(producerTask, 0, periodInMillis);

        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                timer.cancel(); 
            }
        }, durationInMillis);
    }
}

