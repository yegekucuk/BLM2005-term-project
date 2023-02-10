package com.example.donemprojesi;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    public static Stage primaryStage;
    public static Doctor doktor1 = new Doctor("Doktor", "Bir", 76, "123", "123");

    @Override
    public void start(Stage stage) throws IOException {
        primaryStage = stage;
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("GirisEkrani.fxml"));
        Scene scene1 = new Scene(fxmlLoader.load());
        primaryStage.setTitle("Panel");
        primaryStage.setScene(scene1);
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
