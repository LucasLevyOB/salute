package com.salute.salute.java.controller;

import com.salute.salute.java.abstratta.Controller;
import com.salute.salute.java.interfaces.Formulario;
import com.salute.salute.java.schemas.Usuario;
import com.salute.salute.java.singleton.AppStore;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

public class Login extends Controller implements Formulario {
  private AppStore appStore = AppStore.getInstance();

  @FXML
  private TextField inputUsuario;
  @FXML
  private TextField inputSenha;
  @FXML
  private Button btnEntrar;

  @FXML
  private void initialize() {
    btnEntrar.setOnAction(event -> login());
  }

  public void limparCampos() {
    inputUsuario.setText("");
    inputSenha.setText("");
  }

  public boolean validateFields() {
    String usuario = inputUsuario.getText();
    String senha = inputSenha.getText();
    if (usuario.isEmpty() || senha.isEmpty()) {
      return false;
    }
    return true;
  }

  private void login() {
    if (!validateFields()) {
      AlertDialog.show(AlertType.ERROR, btnEntrar.getScene().getWindow(), "Formulário Inválido",
          "Preencha todos os campos");
      return;
    }

    String usuario = inputUsuario.getText();
    String senha = inputSenha.getText();
    boolean result = Usuario.login(usuario, senha);

    if (!result) {
      Notification.showNotification("Login", "Usuário ou senha incorretos");
      return;
    }

    Notification.showNotification("Login", "Login realizado com sucesso");
    appStore.setLogged(result);
    this.switchScene("main", btnEntrar);
    limparCampos();
  }
}
