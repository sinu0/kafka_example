package edu.my.example.kafka;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.concurrent.TimeUnit;

import kafka.server.KafkaServer;
import org.junit.ClassRule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.kafka.test.rule.KafkaEmbedded;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@DirtiesContext
public class SpringKafkaApplicationTest {
  private static final Logger log = LoggerFactory.getLogger(SpringKafkaApplicationTest.class);
  private static String BOOT_TOPIC = "boot.t";

  @Autowired
  private Sender sender;

  @Autowired
  private Consumer receiver;

  @ClassRule
  public static KafkaEmbedded embeddedKafka =
          new KafkaEmbedded(1, true, BOOT_TOPIC);
  @Test
  public void testReceive() throws Exception {
    sender.send(BOOT_TOPIC, "Hello Boot!");

    receiver.getLatch().await(10000, TimeUnit.MILLISECONDS);
    assertThat(receiver.getLatch().getCount()).isEqualTo(0);
  }
}