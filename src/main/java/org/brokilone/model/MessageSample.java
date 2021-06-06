package org.brokilone.model;

import java.util.UUID;

/**
 * @author Kseniia Ushakova
 */
public class MessageSample implements BrokerMessage {
  private final UUID id = UUID.randomUUID();

  @Override
  public String toString() {
    return "MessageSample(id=" + this.id + ")";
  }

}
