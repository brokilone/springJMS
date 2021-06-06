package org.brokilone.spring;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.brokilone.jms.MessageObject;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import javax.jms.Message;

/**
 * @author Kseniia Ushakova
 */
@Component
public class MessageListener {
  private final static Logger logger = LogManager.getLogger(MessageListener.class);

  @JmsListener(destination = "${brokilone.jms.queue}")
  public void receiveMessage(final Message message) {
    logger.info("recieved message - {}", message);
  }

  @JmsListener(destination = "${brokilone.jms.queueObject}")
  public void receiveObjectMessage(final MessageObject message) {
    logger.info("recieved message - {}", message);
  }
}
