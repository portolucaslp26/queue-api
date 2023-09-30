package com.weon.devel.channel;

import java.util.UUID;

public interface Channel {
    void executeCommand();
    public abstract UUID getId();

}