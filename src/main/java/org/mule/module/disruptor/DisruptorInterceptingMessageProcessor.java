package org.mule.module.disruptor;

import com.lmax.disruptor.EventHandler;
import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.dsl.Disruptor;
import org.mule.DefaultMuleEvent;
import org.mule.api.MuleEvent;
import org.mule.api.MuleException;
import org.mule.api.processor.MessageProcessor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class DisruptorInterceptingMessageProcessor implements MessageProcessor {

    RingBuffer<MuleEventHolder> ringBuffer;

    public DisruptorInterceptingMessageProcessor(MessageProcessor processor) {

        ExecutorService exec = Executors.newCachedThreadPool();
        Disruptor<MuleEventHolder> disruptor = new Disruptor<MuleEventHolder>(new MuleEventFactory(), 1024, exec);

        EventHandler handler = new MuleEventHandler(processor);
        disruptor.handleEventsWith(handler);
        ringBuffer = disruptor.start();
    }

    @Override
    public MuleEvent process(MuleEvent e) throws MuleException {
        DefaultMuleEvent event = (DefaultMuleEvent) e;
        long sequence = ringBuffer.next();
        MuleEventHolder eventHolder = ringBuffer.get(sequence);
        eventHolder.setEvent((MuleEvent) event.newThreadCopy());
        ringBuffer.publish(sequence);
        return event;
    }


}
