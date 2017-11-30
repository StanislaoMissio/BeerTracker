package com.android.beertracker.entity;


import com.google.gson.annotations.SerializedName;

import android.os.Parcel;
import android.os.Parcelable;

public class Harmonia implements Parcelable{

    @SerializedName("cod_harmonia")
    private int codHarmonia;
    @SerializedName("nome_harmonia")
    private String nomeHarmonia;
    private String descricao;
    private String tipo;

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
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
        out.writeString(tipo);
    }

    public Harmonia(Parcel in){
        codHarmonia = in.readInt();
        nomeHarmonia = in.readString();
        descricao = in.readString();
        tipo = in.readString();
    }

    public static final Creator<Harmonia> CREATOR = new Creator<Harmonia>() {
        @Override
        public Harmonia createFromParcel(Parcel in) {
            return new Harmonia(in);
        }

        @Override
        public Harmonia[] newArray(int size) {
            return new Harmonia[size];
        }
    };
}
