package com.salute.salute.java.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main {
  private Stage stage;
  private Scene scene;
  private Parent root;

  @FXML
  public void switchScene(ActionEvent event) throws Exception {
    try {
      Node node = (Node) event.getSource();
      String view = (String) node.getUserData();
      root = FXMLLoader.load(getClass().getResource("../view/" + view + ".fxml"));
      scene = new Scene(root);
      scene.getStylesheets().add(getClass().getResource("../styles.css").toExternalForm());
      stage = (Stage) node.getScene().getWindow();
      stage.setScene(scene);
      stage.show();
    } catch (Exception e) {
      System.out.println(e);
    }
  }
}
