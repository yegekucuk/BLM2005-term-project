package com.example.donemprojesi;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

public class HastaEkleController {
    @FXML
    private Label label;
    @FXML
    private TextField txIsimEkle;
    @FXML
    private TextField txSoyadEkle;
    @FXML
    private TextField txKimlikEkle;
    @FXML
    private TextField txYasEkle;

    @FXML
    void hastaEkle() {
        ArrayList<String> IDler = new ArrayList<>();
        try {
            for (Patient patient : Controller.currentDoctor.getPatients()) {
                IDler.add(patient.getKimlikNo());
            }
            if (!IDler.contains(txKimlikEkle.getText())) {
                if (txIsimEkle.getText().equals("") || txSoyadEkle.getText().equals("") || txYasEkle.getText().equals("") || txKimlikEkle.getText().equals("")) {
                    throw new Exception();
                } else {
                    Controller.currentDoctor.addPatient(txIsimEkle.getText(), txSoyadEkle.getText(), Integer.parseInt(txYasEkle.getText()), txKimlikEkle.getText());
                    FileWriter writer = new FileWriter("src\\main\\java\\com\\example\\donemprojesi\\patients.txt", true);
                    String string = String.format("%s;%s;%s;%s;", txIsimEkle.getText(), txSoyadEkle.getText(), txYasEkle.getText(), txKimlikEkle.getText());
                    writer.write(string);
                    writer.close();
                    label.setTextFill(Color.valueOf("#00ae34"));
                    label.setText("Hasta eklendi!");
                }
            } else {
                label.setTextFill(Color.RED);
                label.setText("ID kullaniliyor!");
            }

        } catch (Exception exception) {
            label.setTextFill(Color.RED);
            label.setText("Hasta eklenemedi!");
        }


    }

    @FXML
    void geriDon() throws IOException {
        Scene scene6 = new Scene(FXMLLoader.load(Objects.requireNonNull(getClass().getResource("AnaEkran.fxml"))));
        Main.primaryStage.setScene(scene6);
        Main.primaryStage.show();
    }
}
