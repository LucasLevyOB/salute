/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.salute.salute.java;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author lucas-levy
 */
public class SaluteJava extends Application {

    public static void main(String[] args) throws Exception {
        launch();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent login = FXMLLoader.load(getClass().getResource("view/AlocacaoAutomatica.fxml"));
        Scene scene = new Scene(login);
        scene.getStylesheets().add(getClass().getResource("styles.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.setTitle("Alocação Automática");
        primaryStage.setResizable(false);
        primaryStage.show();
    }
}