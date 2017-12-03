package com.android.beertracker.entity;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.sql.Date;
import java.util.HashMap;

public class User implements Parcelable {

    private String usuario;
    private String senha;
    private String email;

    private HashMap<String, String> listUser = new HashMap<>();

    public User(String usuario, String senha, String email){
        this.usuario = usuario;
        this.senha = senha;
        this.email = email;
    }

    public void addToJson(){
        listUser.put("usuario", this.usuario);
        listUser.put("senha", this.usuario);
        listUser.put("email", this.usuario);
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public HashMap<String, String> getListUser() {
        return listUser;
    }

    public void setListUser(HashMap<String, String> listUser) {
        this.listUser = listUser;
    }

    protected User(Parcel in) {
        usuario = in.readString();
        senha = in.readString();
        email = in.readString();

    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(usuario);
        parcel.writeString(senha);
        parcel.writeString(email);
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };
}