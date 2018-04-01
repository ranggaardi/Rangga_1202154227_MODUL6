package com.example.android.rangga_1202154227_modul6;

/**
 * Created by ranggaardi on 4/1/2018.
 */

public class Database {
    public String image, title, caption, user, key;


    public Database() {
    }


    public Database(String caption, String image, String title, String user) {
        this.image = image;
        this.title = title;
        this.caption = caption;
        this.user = user;
    }


    public String getKey() {
        return key;
    }


    public void setKey(String key) {
        this.key = key;
    }


    public String getImage() {
        return image;
    }

    public String getTitle() {
        return title;
    }

    public String getCaption() {
        return caption;
    }

    public String getUser() {
        return user;
    }

}

