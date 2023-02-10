// GirisEkrani.fxml

package com.example.donemprojesi;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.util.Objects;

public class GirisEkraniController {
    public static Doctor currentDoctor;

    @FXML
    private TextField idField;

    @FXML
    private PasswordField pwField;

    @FXML
    private void giris() throws IOException {
        currentDoctor = Main.doktor1;
        String girilenID = idField.getText();
        String girilenPW = pwField.getText();
        if (girilenID.equals(currentDoctor.getId()) && girilenPW.equals(currentDoctor.getPassword())) {
            Scene scene2 = new Scene(FXMLLoader.load(Objects.requireNonNull(getClass().getResource("AnaEkran.fxml"))));
            Main.primaryStage.setScene(scene2);
            Main.primaryStage.show();
        }
        else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Title");
            alert.setContentText("");
            alert.setHeaderText("Kullanıcı adı veya şifre hatalı.");
            alert.showAndWait();
        }
    }
}
