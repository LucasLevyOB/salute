package com.salute.salute.java.controller;

import org.kordamp.ikonli.javafx.FontIcon;

import com.salute.salute.java.interfaces.CallbackTableButton;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.util.Callback;

public class TableButton<T> {
  public void adicionaColuna(TableColumn<T, Void> coluna, String icon,
      CallbackTableButton<T> callback) {
    Callback<TableColumn<T, Void>, TableCell<T, Void>> cellFactory = new Callback<TableColumn<T, Void>, TableCell<T, Void>>() {
      @Override
      public TableCell<T, Void> call(
          final TableColumn<T, Void> param) {
        return new TableCell<T, Void>() {

          private final Button btn = new Button();

          {
            btn.setOnAction((ActionEvent event) -> {
              T entity = getTableView().getItems().get(getIndex());
              callback.callback(entity);
            });
          }

          @Override
          public void updateItem(Void item, boolean empty) {
            super.updateItem(item, empty);
            if (empty) {
              setGraphic(null);
            } else {
              styleButton(btn, icon);
              setGraphic(btn);
            }
          }
        };
      }
    };

    coluna.setCellFactory(cellFactory);
  }

  private void styleButton(Button btn, String icon) {
    btn.getStyleClass().add("btn");
    btn.getStyleClass().add("secondary");
    btn.getStyleClass().add("small");
    btn.getStyleClass().add("outline");
    FontIcon btnIcon = new FontIcon();
    btnIcon.setIconLiteral(icon);
    btn.setGraphic(btnIcon);
  }
}
