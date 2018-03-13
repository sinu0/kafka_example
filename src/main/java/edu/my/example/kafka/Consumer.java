package edu.my.example.kafka;

import org.apache.kafka.clients.consumer.ConsumerRebalanceListener;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.concurrent.CountDownLatch;

@Component
public class Consumer {
    private static final Logger log = LoggerFactory.getLogger(Consumer.class);
    private CountDownLatch latch = new CountDownLatch(1);

    public CountDownLatch getLatch() {
        return latch;
    }

    @KafkaListener(topics = "${kafka.topic.boot}")
    public void recevied(ConsumerRecord<?,?> consumerRecord){
        log.info("received payload='{}'", consumerRecord.toString());
        latch.countDown();
    }
}
