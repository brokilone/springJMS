package org.brokilone.spring;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.brokilone.model.BrokerMessage;
import org.brokilone.model.MessageSample;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

/**
 * @author Kseniia Ushakova
 */
@Component
public class ActiveMqReceiver {
  private final static Logger logger = LogManager.getLogger(ActiveMqReceiver.class);

  @JmsListener(destination = "${app.tjmQueue}", containerFactory = "jmsFactory")
  public void receiveMessage(BrokerMessage message) {
    if (message instanceof MessageSample) {
      logger.info("MessageSample: {}", message);
    } else {
      logger.warn("Unknown format");
    }
  }

}
