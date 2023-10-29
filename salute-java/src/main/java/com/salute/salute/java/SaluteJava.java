/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.salute.salute.java;

import com.salute.salute.java.database.Database;

import atlantafx.base.theme.PrimerLight;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
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
        Database.createSchemas();
        Parent startView = FXMLLoader.load(getClass().getResource("view/main.fxml"));
        Scene scene = new Scene(startView);
        Application.setUserAgentStylesheet(new PrimerLight().getUserAgentStylesheet());
        scene.getStylesheets().add(getClass().getResource("styles.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.setTitle("SALUTE");
        primaryStage.show();
    }
}