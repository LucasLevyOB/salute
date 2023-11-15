package com.salute.salute.java.controller;

import org.controlsfx.control.Notifications;

public class Notification {

  private Notification() {
  }

  public static void showNotification(String title, String text) {
    Notifications notification = Notifications.create()
        .title(title)
        .text(text)
        .hideAfter(javafx.util.Duration.seconds(5))
        .position(javafx.geometry.Pos.BOTTOM_RIGHT);

    notification.show();
  }
}
