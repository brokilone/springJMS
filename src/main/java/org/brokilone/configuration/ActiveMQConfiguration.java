package org.brokilone.configuration;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.connection.JmsTransactionManager;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.jms.support.converter.MessageType;

import java.util.List;
import javax.jms.ConnectionFactory;
import javax.jms.Session;

/**
 * @author Kseniia Ushakova
 */
@EnableJms
@Configuration
@ConfigurationProperties(prefix = "app")
public class ActiveMQConfiguration {
  private static final Logger logger = LogManager.getLogger(ActiveMQConfiguration.class);

  private String brokerUrl;
  private String userName;
  private String password;
  private String tjmQueue;
  private String messageType;


  @Bean
  public ConnectionFactory connectionFactory() {
    logger.info("Init connection factory");
    var factory = new ActiveMQConnectionFactory();
    factory.setBrokerURL(brokerUrl);
    factory.setUserName(userName);
    factory.setPassword(password);
    factory.setTrustedPackages(List.of("org.brokilone"));
    return factory;
  }

  @Bean
  public MessageConverter jacksonJMSMessageConverter(){
    logger.info("Init meassage converter");
    var converter = new MappingJackson2MessageConverter();
    converter.setTargetType(MessageType.valueOf(messageType));
    converter.setTypeIdPropertyName("_type");
    return converter;
  }

  @Bean
  public DefaultJmsListenerContainerFactory jmsFactory(){
    var factory = new DefaultJmsListenerContainerFactory();
    factory.setConnectionFactory(connectionFactory());
    factory.setMessageConverter(jacksonJMSMessageConverter());
    factory.setSessionAcknowledgeMode(Session.AUTO_ACKNOWLEDGE);
    factory.setConcurrency("1-1");
    return factory;
  }

  @Bean
  public JmsTransactionManager jmsTransactionManager(){
    var transactionManager = new JmsTransactionManager();
    transactionManager.setConnectionFactory(connectionFactory());
    return transactionManager;
  }

  @Bean
  public JmsTemplate jmsTemplate(){
    var jmsTemplate = new JmsTemplate();
    jmsTemplate.setMessageConverter(jacksonJMSMessageConverter());
    jmsTemplate.setSessionTransacted(true);
    jmsTemplate.setConnectionFactory(connectionFactory());
    return jmsTemplate;
  }

  public void setBrokerUrl(String brokerUrl) {
    this.brokerUrl = brokerUrl;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public void setTjmQueue(String tjmQueue) {
    this.tjmQueue = tjmQueue;
  }

  public void setMessageType(String messageType) {
    this.messageType = messageType;
  }
}
