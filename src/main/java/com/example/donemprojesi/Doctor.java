package com.example.donemprojesi;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Doctor extends Person implements FileController {
    ArrayList<Patient> patients = new ArrayList<>();

    private String id;
    private String password;

    public String getId() {
        return id;
    }

    public String getPassword() {
        return password;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Doctor() {
    }

    public Doctor(String name, String surname, int age, String id, String password) {
        super(name,surname,age);
        this.setId(id);
        this.setPassword(password);
        this.readPatients();
    }

    // Not defterinden okunan hastalari ArrayList'e ekler
    void addPatient(Patient p) {
        patients.add(p);
    }
    // Overloading : Bilgisi elle girilen yeni hastalari ArrayList'e ekler.
    void addPatient(String name, String surname, int age, String kimlikNo) {
        Patient p = new Patient(name,surname,age,kimlikNo);
        patients.add(p);
    }

    ArrayList<Patient> getPatients() {
        return patients;
    }

    @Override
    public void read(String dosyaAdi) throws FileNotFoundException {
        String k = String.format("src\\main\\java\\com\\example\\donemprojesi\\%s", dosyaAdi);
        Scanner sc = new Scanner(new File(k));
        sc.useDelimiter(";");
        while (sc.hasNext()) {
            addPatient(new Patient(sc.next(), sc.next(), sc.nextInt(), sc.next()));
        }
        sc.close();
    }

    void readPatients() {
        try {
            read("patients.txt");
        }
        catch (FileNotFoundException exception) {
            System.out.println("Dosya bulunamadigindan doktorun hastalari eklenemedi.");
        }
    }
}
