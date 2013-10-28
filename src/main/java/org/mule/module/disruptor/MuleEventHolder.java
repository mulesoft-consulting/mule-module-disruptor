package org.mule.module.disruptor;

import org.mule.api.MuleEvent;

public class MuleEventHolder {

    MuleEvent event;

    public MuleEvent getEvent() {
        return event;
    }

    public void setEvent(MuleEvent event) {
        this.event = event;
    }
}
