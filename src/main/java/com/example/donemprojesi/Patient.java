package com.example.donemprojesi;

public class Patient extends Person {
    private String kimlikNo;

    public String getKimlikNo() {
        return kimlikNo;
    }

    public void setKimlikNo(String kimlikNo) {
        this.kimlikNo = kimlikNo;
    }

    public Patient(String name, String surname, int age, String kimlikNo) {
        super(name,surname,age);
        this.setKimlikNo(kimlikNo);
    }
}
