package com.weon.devel.consumer;

import com.weon.devel.channel.Channel;

public interface Consumer {
    void consume(Channel channel);
}
