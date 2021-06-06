package org.brokilone;

import org.brokilone.configuration.ActiveMQConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

/**
 * @author Kseniia Ushakova
 */
@SpringBootApplication
@EnableConfigurationProperties({ActiveMQConfiguration.class})
public class SpringJMSApplication {
  public static void main(String[] args) {
    SpringApplication.run(SpringJMSApplication.class, args);
  }
}
