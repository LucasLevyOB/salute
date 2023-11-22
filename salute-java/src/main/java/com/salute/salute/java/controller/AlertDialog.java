package com.salute.salute.java.controller;

import javafx.scene.control.Alert;
import javafx.stage.Window;

public class AlertDialog {

  private AlertDialog() {
  }

  public static void show(Alert.AlertType alertType, Window owner, String title, String message) {
    Alert alert = new Alert(alertType);
    alert.setTitle(title);
    alert.setHeaderText(null);
    alert.setContentText(message);
    alert.initOwner(owner);
    alert.show();
  }
}