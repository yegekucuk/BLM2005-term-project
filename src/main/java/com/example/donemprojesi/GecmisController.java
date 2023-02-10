// GecmisEkrani.fxml

package com.example.donemprojesi;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;

import java.io.*;
import java.net.URL;
import java.util.ResourceBundle;

public class GecmisController implements Initializable, FileController {

    @FXML
    private TextArea txGecmisReceteler;

    @FXML
    private TextArea txGecmisSikayetler;

    @Override
    public void read(String dosyaAdi) {
        String recete = String.format("src\\main\\java\\com\\example\\donemprojesi\\receteler\\%s.txt", dosyaAdi);
        String sikayet = String.format("src\\main\\java\\com\\example\\donemprojesi\\randevular\\%s.txt", dosyaAdi);
        File receteF = new File(recete);
        File sikayetF = new File(sikayet);
        if (receteF.exists() && sikayetF.exists()) {
            try {
                FileReader receteReader = new FileReader(receteF);
                FileReader sikayetReader = new FileReader(sikayetF);
                StringBuilder receteStr = new StringBuilder();
                StringBuilder sikayetStr = new StringBuilder();
                int i1, i2;
                char c1, c2;
                while ((i1 = receteReader.read()) != -1) {
                    c1 = (char) i1;
                    receteStr.append(c1);
                }

                while ((i2 = sikayetReader.read()) != -1) {
                    c2 = (char) i2;
                    sikayetStr.append(c2);
                }
                receteReader.close();
                sikayetReader.close();
                txGecmisReceteler.setText(receteStr.toString());
                txGecmisSikayetler.setText(sikayetStr.toString());

            } catch (IOException exception) {
                System.out.println(exception.getMessage());
                txGecmisSikayetler.setText("");
                txGecmisReceteler.setText("");
            }
        } else {
            try {
                boolean sikayetDosyasiOlusturuldu = sikayetF.createNewFile();
                boolean receteDosyasiOlusturuldu = receteF.createNewFile();
                if (sikayetDosyasiOlusturuldu && receteDosyasiOlusturuldu) {
                    FileWriter sikayetWriterOnce = new FileWriter(sikayetF, true);
                    FileWriter receteWriterOnce = new FileWriter(receteF, true);
                    sikayetWriterOnce.write("");
                    receteWriterOnce.write("");
                    sikayetWriterOnce.close();
                    receteWriterOnce.close();
                }
            } catch (IOException exception) {
                System.out.println(exception.getMessage());
            }
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        read(Controller.ID);
    }
}
