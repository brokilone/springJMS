package org.brokilone.configuration;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.brokilone.model.MessageSample;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Kseniia Ushakova
 */
@EnableKafka
@Configuration
@ConfigurationProperties(prefix = "kafbroker")
public class KafkaConfiguration {

  private String bootstrapAddress;
  private String groupId;

  public ConsumerFactory<String, MessageSample> transactionJsonMessageResultConsumerFactory() {
    var config = getConfig();
    return new DefaultKafkaConsumerFactory<String, MessageSample>(config,
        new StringDeserializer(),
        new JsonDeserializer<>(MessageSample.class));
  }

  public Map<String, Object> getConfig() {
    var configProps = new HashMap<String, Object>();

    configProps.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress);
    configProps.put(ConsumerConfig.GROUP_ID_CONFIG, groupId);
    configProps.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
    configProps.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
    return configProps;
  }

  @Bean
  public ConcurrentKafkaListenerContainerFactory<String, MessageSample> transactionJsonMessageResultKafkaListenerContainerFactory() {
    var factory = new ConcurrentKafkaListenerContainerFactory<String, MessageSample>();
    factory.setConsumerFactory(transactionJsonMessageResultConsumerFactory());
    return factory;
  }

  public void setBootstrapAddress(String bootstrapAddress) {
    this.bootstrapAddress = bootstrapAddress;
  }

  public void setGroupId(String groupId) {
    this.groupId = groupId;
  }
}
