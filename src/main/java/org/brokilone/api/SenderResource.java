package org.brokilone.api;

import org.brokilone.jms.MessageObject;
import org.brokilone.spring.Sender;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Kseniia Ushakova
 */
@RestController
public class SenderResource {
  private final Sender sender;
  @Value("${brokilone.jms.queue}")
  private String queueName;

  @Value("${brokilone.jms.queueObject}")
  private String queueObjectName;

  public SenderResource(Sender sender) {
    this.sender = sender;
  }

  @PostMapping("/send")
  public String send(@RequestBody String message) {
    sender.sendMessage(queueName, message);
    return "sended text";
  }

  @PostMapping("/sendobject")
  public String sendObject(@RequestBody MessageObject messageObject) {
    sender.sendMessageObject(queueObjectName, messageObject);
    return "sended object";
  }
}
