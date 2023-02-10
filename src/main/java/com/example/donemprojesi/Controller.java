// AnaEkran.fxml

package com.example.donemprojesi;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    public static Doctor currentDoctor;
    @FXML
    private Label label;

    @FXML
    private Button btChangeHastaEkle;

    @FXML
    private Button btLoadPatients;

    @FXML
    private Button btHastayiKapat;

    @FXML
    private Button btGecmisRandevular;

    @FXML
    private Button btClearRecete;
    @FXML
    private Button btKaydet;
    @FXML
    private TextField yas;

    @FXML
    private TextField isim;

    @FXML
    private TextField kimlik;

    // Gecmis Ekrani Controller dosyasında ID isimli static degisken cagiriliyor. Ana ekranda hangi hastanın bilgilerinin acik oldugunu gecmis ekranindan gormeyi sagliyor.
    public static String ID;

    @FXML
    private ChoiceBox<String> patientsCbox;

    @FXML
    private TextField soyisim;

    @FXML
    private TextArea txRecete;

    @FXML
    private TextArea txSikayet;

    // Gosterilen ekrani gecmis ekranina degistiren fonksiyon.
    // Ana ekrandaki bilgilerin silinmeden korunmasi icin yeni bir stage olusturuldu.
    @FXML
    void changeGecmisScene() throws IOException {
        Stage stage2 = new Stage();
        Scene scene4 = new Scene(FXMLLoader.load(Objects.requireNonNull(getClass().getResource("GecmisEkrani.fxml"))));
        stage2.setTitle("Gecmis");
        stage2.setScene(scene4);
        stage2.setResizable(false);
        Main.primaryStage.close();
        stage2.showAndWait();
        Main.primaryStage.show();
    }

    @FXML
    void changeHastaEkle() throws IOException {
        Scene scene5 = new Scene(FXMLLoader.load(Objects.requireNonNull(getClass().getResource("HastaEkle.fxml"))));
        Main.primaryStage.setScene(scene5);
        Main.primaryStage.show();
    }

    // Giris ekranina donduren fonksiyon.
    @FXML
    private void cikis() throws IOException {
        Scene scene3 = new Scene(FXMLLoader.load(Objects.requireNonNull(getClass().getResource("GirisEkrani.fxml"))));
        Main.primaryStage.setScene(scene3);
        Main.primaryStage.show();
    }

    // Recete ve Sikayet metin kutularini temizler.
    @FXML
    void clearRecete() {
        txRecete.clear();
        txSikayet.clear();
        label.setTextFill(Color.valueOf("#00ae34"));
        label.setText("Başarıyla temizlendi!");
    }

    void clearReceteWithoutLabel() {
        txRecete.clear();
        txSikayet.clear();
    }

    // ChoiceBox'dan sectigimiz hastanin bilgileri ekrana yuklenir.
    @FXML
    void loadPatient() {
        if (patientsCbox.getValue() != null) {
            isim.setText(patientsCbox.getValue());
            for (Patient patient : Main.doktor1.getPatients()) {
                if (patient.getName().equals(isim.getText())) {
                    // Bu static degisken Gecmis Ekraninda hastanin IDsine erismek icin tutuluyor.
                    ID = patient.getKimlikNo();
                    soyisim.setText(patient.getSurname());
                    kimlik.setText(patient.getKimlikNo());
                    yas.setText(String.valueOf(patient.getAge()));
                }
            }
            txSikayet.setDisable(false);
            txRecete.setDisable(false);
            btClearRecete.setDisable(false);
            btKaydet.setDisable(false);
            btHastayiKapat.setDisable(false);
            btGecmisRandevular.setDisable(false);
            btLoadPatients.setDisable(true);
            patientsCbox.setDisable(true);
            btChangeHastaEkle.setDisable(true);
        } else System.out.println("Hata!");
    }

    @FXML
    void save() {
        if (!txSikayet.getText().equals("") && !txRecete.getText().equals("")) {
            write(kimlik.getText());
            clearReceteWithoutLabel();
            label.setTextFill(Color.valueOf("#00ae34"));
            label.setText("Başarıyla kaydedildi!");
        } else {
            label.setTextFill(Color.RED);
            label.setText("Kaydedilemedi!");
        }
    }

    @FXML
    void showContact() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("İletişim");
        alert.setHeaderText("İletişim");
        alert.setContentText("Yunus Ege Küçük 170421048");
        alert.showAndWait();
    }

    @FXML
    void temizle() {
        isim.setText("");
        ID = "";
        soyisim.setText("");
        yas.setText("");
        kimlik.setText("");
        txRecete.setText("");
        txSikayet.setText("");
        txSikayet.setDisable(true);
        txRecete.setDisable(true);
        btClearRecete.setDisable(true);
        btKaydet.setDisable(true);
        btHastayiKapat.setDisable(true);
        btGecmisRandevular.setDisable(true);
        patientsCbox.setDisable(false);
        btLoadPatients.setDisable(false);
        btChangeHastaEkle.setDisable(false);
        label.setText("");
    }

    void write(String dosyaAdi) {
        Date date = new Date();
        String recete = String.format("src\\main\\java\\com\\example\\donemprojesi\\receteler\\%s.txt", dosyaAdi);
        String sikayet = String.format("src\\main\\java\\com\\example\\donemprojesi\\randevular\\%s.txt", dosyaAdi);
        File receteF = new File(recete);
        File sikayetF = new File(sikayet);
        try {
            FileWriter receteWriter = new FileWriter(receteF, true);
            FileWriter sikayetWriter = new FileWriter(sikayetF, true);
            receteWriter.write("[" + date + "]  " + txRecete.getText() + "\n");
            sikayetWriter.write("[" + date + "]  " + txSikayet.getText() + "\n");
            receteWriter.close();
            sikayetWriter.close();

        } catch (IOException exception) {
            System.out.println(exception.getMessage());
        }
    }

    @FXML
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        currentDoctor = GirisEkraniController.currentDoctor;
        ArrayList<String> displayNames = new ArrayList<>();
        for (Patient patient : currentDoctor.getPatients()) {
            displayNames.add(patient.getName());
            System.out.println(patient.getName());
        }
        patientsCbox.getItems().addAll(displayNames);
    }
}
