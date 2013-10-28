package org.mule.module.disruptor;


import com.lmax.disruptor.EventHandler;
import org.mule.DefaultMuleEvent;
import org.mule.api.MuleEvent;
import org.mule.api.processor.MessageProcessor;

public class MuleEventHandler implements EventHandler<MuleEventHolder> {

    MessageProcessor messageProcessor;


    public MuleEventHandler(MessageProcessor messageProcessor) {
        this.messageProcessor = messageProcessor;
    }

    @Override
    public void onEvent(MuleEventHolder holder, long sequence, boolean endOfBatch) throws Exception {
        DefaultMuleEvent event = (DefaultMuleEvent) holder.getEvent();
        messageProcessor.process(event);
    }
}
