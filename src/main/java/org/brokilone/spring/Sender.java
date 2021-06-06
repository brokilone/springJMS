package org.brokilone.spring;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.brokilone.configuration.ActiveMQConfiguration;
import org.brokilone.jms.MessageObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

/**
 * @author Kseniia Ushakova
 */
@Component
public class Sender {
  private static final Logger logger = LogManager.getLogger(ActiveMQConfiguration.class);

  @Autowired
  private JmsTemplate jmsTemplate;

  public void sendMessage(final String queueName, final String message) {
    logger.info("Sending message - {} into queue - {}", message, queueName);
    jmsTemplate.setTimeToLive(30000);
    jmsTemplate.convertAndSend(queueName, message);
  }

  public void sendMessageObject(final String queueName, final MessageObject message) {
    logger.info("Sending message - {} into queue - {}", message, queueName);
    jmsTemplate.setTimeToLive(30000);
    jmsTemplate.convertAndSend(queueName, message);
  }
}
