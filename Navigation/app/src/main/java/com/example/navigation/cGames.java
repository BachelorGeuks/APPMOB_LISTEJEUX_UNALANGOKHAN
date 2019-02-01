package com.example.navigation;

import android.graphics.Bitmap;
import android.os.Parcel;
import android.os.Parcelable;

public class cGames implements Parcelable {
    private String name;    //Nom du jeu
    private String text;    //Editeur du jeu
    private Bitmap avatar;  //Image du jeu
    private String path;    //Chemin d'accés de l'image
    private String desc;    //Description du jeu

    public void setDesc(String desc){
        this.desc = desc;
    }

    public String getDesc(){
        return this.desc;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getName(){
        return this.name;
    }

    public void setText(String text){
        this.text = text;
    }

    public String getText(){
        return text;
    }

    public void setAvatar(Bitmap avatar){
        this.avatar = avatar;
    }

    public Bitmap getAvatar(){
        return avatar;
    }

    public void setPath(String path){ this.path = path; }

    public String getPath(){ return this.path; }

    public cGames(String id, String name){
        this.name = id;
        this.text = name;
        this.avatar = null;
    }

    public cGames(){
        this.name = "";
        this.text = "";
        this.avatar = null;
        this.desc = "";
    }

    public cGames(Parcel source){
        this.name = source.readString();
        this.text = source.readString();
        this.path = source.readString();
        this.desc = source.readString();
    }

    @Override
    public int describeContents(){
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.name);
        dest.writeString(this.text);
        dest.writeString(this.path);
        dest.writeString(this.desc);
    }

    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        public cGames createFromParcel(Parcel in) {
            return new cGames(in);
        }

        public cGames[] newArray(int size) {
            return new cGames[size];
        }
    };

}
