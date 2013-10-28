package org.mule.module.disruptor;

import com.lmax.disruptor.EventFactory;
import org.mule.DefaultMuleEvent;

public class MuleEventFactory implements EventFactory<MuleEventHolder> {

    @Override
    public MuleEventHolder newInstance() {
        return new MuleEventHolder();
    }
}
