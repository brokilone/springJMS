package org.brokilone.jms;

import java.io.Serializable;

/**
 * @author Kseniia Ushakova
 */
public class MessageObject implements Serializable {
  private String name;

  public MessageObject() {
  }

  public MessageObject(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  @Override
  public String toString() {
    return "MessageObject{" +
        "name='" + name + '\'' +
        '}';
  }

}
