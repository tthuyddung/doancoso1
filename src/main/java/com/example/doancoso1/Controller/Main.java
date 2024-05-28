package com.example.doancoso1.Controller;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    public void start(Stage stage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("/com/example/doancoso1/View/Login.fxml"));

        Scene scene = new Scene(root);

        stage.setMinWidth(402);
        stage.setMaxHeight(598);

        stage.setTitle("Online Learning System");

        stage.setScene(scene);
        stage.show();
    }
    public static void main(String[] args){
        launch(args);
    }
}