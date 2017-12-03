package com.android.beertracker.entity;


import com.google.gson.annotations.SerializedName;

import android.os.Parcel;
import android.os.Parcelable;

public class Harmony implements Parcelable{

    @SerializedName("cod_harmonia")
    private int codHarmonia;
    @SerializedName("nome_harmonia")
    private String nomeHarmonia;
    private String descricao;
    private String imagem;

    public String getImagem() {
        return imagem;
    }

    public void setImagem(String imagem) {
        this.imagem = imagem;
    }

    public String getNomeHarmonia() {
        return nomeHarmonia;
    }

    public void setNomeHarmonia(String nomeHarmonia) {
        this.nomeHarmonia = nomeHarmonia;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getCodHarmonia() {
        return codHarmonia;
    }

    public void setCodHarmonia(int codHarmonia) {
        this.codHarmonia = codHarmonia;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel out, int i) {
        out.writeLong(codHarmonia);
        out.writeString(nomeHarmonia);
        out.writeString(descricao);
        out.writeString(imagem);
    }

    public Harmony(Parcel in){
        codHarmonia = in.readInt();
        nomeHarmonia = in.readString();
        descricao = in.readString();
        imagem = in.readString();
    }

    public static final Creator<Harmony> CREATOR = new Creator<Harmony>() {
        @Override
        public Harmony createFromParcel(Parcel in) {
            return new Harmony(in);
        }

        @Override
        public Harmony[] newArray(int size) {
            return new Harmony[size];
        }
    };
}
