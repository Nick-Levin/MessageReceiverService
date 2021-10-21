package org.messageReceiverService;

import org.messageReceiverService.model.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Component
public class ConsumerSender implements CommandLineRunner {

    @Autowired
    BlockingQueue<Message> blockingQueue;

    @Autowired
    RestTemplate restTemplate;

    /**
     * The Sender thread is running asynchronously and uses blocking queue <code>take</code> method
     * which prevents 100% CPU usage despite the <code>while(true)</code> loop.
     * @param args
     * @throws Exception
     */
    @Override
    public void run(String ...args) throws Exception {
        ExecutorService service = Executors.newSingleThreadExecutor();
        service.execute(() -> {
            while(true) {
                try {
                    Message message = blockingQueue.take();
                    String url = "http://localhost:9001/message?message={MSG}&number={NUM}"
                            .replace("{MSG}", message.getMessage())
                            .replace("{NUM}", message.getNumber().toString());
                    restTemplate.getForObject(url, String.class);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    throw new RuntimeException(e.getMessage());
                }
            }
        });
    }

}
