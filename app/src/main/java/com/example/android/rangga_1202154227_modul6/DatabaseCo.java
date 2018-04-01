package com.example.android.rangga_1202154227_modul6;

/**
 * Created by ranggaardi on 4/1/2018.
 */

public class DatabaseCo {
    String yangkomen, komennya, fotoyangdikomen;

    public DatabaseCo(){

    }


    public DatabaseCo(String yangkomen, String komennya, String fotoyangdikomen) {
        this.yangkomen = yangkomen;
        this.komennya = komennya;
        this.fotoyangdikomen = fotoyangdikomen;
    }


    public String getFotoyangdikomen() {
        return fotoyangdikomen;
    }

    public String getYangkomen() {
        return yangkomen;
    }

    public String getKomennya() {
        return komennya;
    }

}

