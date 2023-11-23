package com.salute.salute.java.abstratta;

import com.salute.salute.java.singleton.AppStore;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public abstract class Controller {
  private Stage stage;
  private Scene scene;
  private Parent root;

  protected AppStore appStore = AppStore.getInstance();

  /**
   * Método chamado antes de trocar de tela
   * Ele deve retornar true para permitir a troca de tela
   * ou false para cancelar a troca de tela
   * 
   * @return boolean true para permitir a troca de tela
   */
  public boolean onChangeStage() {
    return true;
  }

  @FXML
  public void switchScene(ActionEvent event) throws Exception {
    try {
      if (!onChangeStage()) {
        return;
      }

      Node node = (Node) event.getSource();
      String view = (String) node.getUserData();

      if (!appStore.isLogged() && !view.equals("Login")) {
        return;
      }

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
