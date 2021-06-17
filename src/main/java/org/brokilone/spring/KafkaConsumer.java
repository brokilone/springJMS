package org.brokilone.spring;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.brokilone.model.BrokerMessage;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

/**
 * @author Kseniia Ushakova
 */

@Service
public class KafkaConsumer {
  private final static Logger logger = LogManager.getLogger(KafkaConsumer.class);
  @KafkaListener(topics = "${app.tjmQueue}", containerFactory = "transactionJsonMessageResultKafkaListenerContainerFactory")
  public void messageListener(BrokerMessage message) {
    logger.info("Received message " + message);
  }
}
