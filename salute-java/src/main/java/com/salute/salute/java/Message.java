package com.salute.salute.java;

import javafx.scene.control.Alert.AlertType;

public class Message {
  private String content;
  private AlertType tipo;

  public Message(String content, AlertType tipo) {
    this.content = content;
    this.tipo = tipo;
  }

  public Message() {
    this.content = "";
    this.tipo = AlertType.NONE;
  }

  public String getMessage() {
    return content;
  }

  public AlertType getTipo() {
    return tipo;
  }

  public void setMessage(String content) {
    this.content = content;
  }

  public void setTipo(AlertType tipo) {
    this.tipo = tipo;
  }
}
