package com.example.donemprojesi;

import java.io.FileNotFoundException;

public interface FileController {
    void read(String dosyaAdi) throws FileNotFoundException;
}
