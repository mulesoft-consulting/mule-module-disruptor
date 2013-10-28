package org.mule.module.disruptor;

import org.mule.api.MuleContext;
import org.mule.api.processor.MessageProcessor;
import org.mule.api.processor.MessageProcessorBuilder;
import org.mule.api.processor.MessageProcessorChainBuilder;
import org.mule.api.processor.ProcessingStrategy;
import org.mule.processor.strategy.SynchronousProcessingStrategy;

import java.util.ArrayList;
import java.util.List;

public class DisruptorProcessingStrategy implements ProcessingStrategy {

    @Override
    public void configureProcessors(List<MessageProcessor> processors,
                                    StageNameSource nameSource,
                                    MessageProcessorChainBuilder builder,
                                    MuleContext muleContext)
    {


        for (int i = 0; i < processors.size(); i++)
        {
            MessageProcessor processor = processors.get(i);

            if (processor instanceof MessageProcessor)
            {
                builder.chain(new DisruptorInterceptingMessageProcessor(processor));
            }
            else if (processor instanceof MessageProcessorBuilder)
            {
                builder.chain((MessageProcessorBuilder) processor);
            }
            else
            {
                throw new IllegalArgumentException(
                        "MessageProcessorBuilder should only have MessageProcessor's or MessageProcessorBuilder's configured");
            }
        }

//        List<MessageProcessor> wrappedProcessors = new ArrayList<MessageProcessor>(processors.size());
//
//        for (MessageProcessor processor : wrappedProcessors) {
//            wrappedProcessors.add(new DisruptorInterceptingMessageProcessor(processor));
//        }
//
//
//
//        synchronousProcessingStrategy.configureProcessors(wrappedProcessors, nameSource, chainBuilder,
//                muleContext);


//        if (wrappedProcessors.size() > 0)
//        {
//            chainBuilder.chain(new DisruptorInterceptingMessageProcessor());
//            synchronousProcessingStrategy.configureProcessors(processors, nameSource, chainBuilder,
//                    muleContext);
//        }
    }

}
