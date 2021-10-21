package org.messageReceiverService.config;

import org.messageReceiverService.model.Message;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

@Configuration
public class QueueConfiguration {

    @Bean
    public BlockingQueue<Message> queue() {
        return new LinkedBlockingQueue<>();
    }

}
