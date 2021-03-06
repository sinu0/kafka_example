package edu.my.example.kafka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class Sender {
    private static final Logger log = LoggerFactory.getLogger(Sender.class);

    @Autowired
    private KafkaTemplate<String,String> kafkaTemplate;

    public void send(String topic, String payload){
        int i=10;
        while(i>0) {
            log.info("sending payload='{}' to topic ='{}'", payload, topic);
            kafkaTemplate.send(topic, payload);
            i--;
        }
    }
}
