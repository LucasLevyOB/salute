package com.salute.salute.java.controller;

import com.salute.salute.java.Alocacao;
import com.salute.salute.java.Turma;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Popup;
import javafx.stage.Stage;

public class Main {
  private Stage stage;
  private Scene scene;
  private Parent root;
  private Popup popup;

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

  @FXML
  public void openGerarRelatorio() {
    try {
      FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/PopupGerarRelatorios.fxml"));
      Node node = loader.load();
      PopupGerarRelatorios controller = loader.getController();
      controller.setCancelar(() -> {
          closePopup();
      });
      openPopup(node);
    } catch (Exception e) {
      System.out.println(e);
    }
  }

  private Stage getStage() {
    return stage;
  }

  private void openPopup(Node node) {
    popup = new Popup();
    popup.setAutoFix(false);
    popup.setHideOnEscape(true);
    popup.getContent().add(node);
    popup.setOnHiding((e) -> {
        popup = null;
    });
    popup.getScene().getRoot().getStyleClass().add("z-elevation-4");
    popup.getScene().getRoot().getStyleClass().add("popup");
    popup.show(this.stage.getWindows().get(0));
  }

  private void closePopup() {
    if (popup != null) {
        popup.hide();
    }
  }
}
