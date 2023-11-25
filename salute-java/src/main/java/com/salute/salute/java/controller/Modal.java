package com.salute.salute.java.controller;

import org.kordamp.ikonli.javafx.FontIcon;

import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Popup;
import javafx.stage.Stage;

public class Modal {
  private Popup popup;
  private Node owner;
  private Node content;
  private String title;

  public Modal(Node owner) {
    this.owner = owner;
    this.popup = new Popup();
    this.popup.setAutoHide(true);
    this.popup.setHideOnEscape(true);
    this.popup.setAutoFix(true);
    this.title = "Modal";
  }

  private Stage getStage() {
    return (Stage) owner.getScene().getWindow();
  }

  private void setStyle(Node node) {
    node.getStyleClass().add("z-elevation-5");
    node.getStyleClass().add("popup");
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public void setContent(Node content) {
    this.content = content;
  }

  private void prepareToClose() {
    this.popup.getContent().clear();
  }

  private Button getBtnClose() {
    Button btn = new Button();
    btn.setOnAction(e -> hide());
    FontIcon icon = new FontIcon();
    icon.setIconLiteral("mdi-close");
    btn.setGraphic(icon);

    return btn;
  }

  private void prepareToShow() {
    VBox root = new VBox();
    root.setSpacing(16);
    root.getStyleClass().add("p-2");
    HBox header = new HBox();
    Text titulo = new Text(title);
    titulo.getStyleClass().add("font-sub-title-2");
    Button btnClose = getBtnClose();
    header.getChildren().add(titulo);
    header.getChildren().add(btnClose);
    header.setAlignment(javafx.geometry.Pos.CENTER_LEFT);
    root.getChildren().add(header);
    root.getChildren().add(this.content);
    root.setPrefWidth(496);
    root.setPrefHeight(400);
    header.setSpacing(root.getPrefWidth() - btnClose.getPrefWidth() - titulo.getLayoutBounds().getWidth() - 32.0);
    setStyle(root);
    popup.getContent().add(root);
  }

  public void show() {
    popup = new Popup();
    prepareToShow();
    this.popup.show(getStage());
  }

  public void hide() {
    prepareToClose();
    popup.hide();
    popup = null;
  }
}
