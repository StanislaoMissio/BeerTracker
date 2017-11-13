package com.android.beertracker.entity;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import android.database.Cursor;
import android.os.Parcel;
import android.os.Parcelable;

import com.android.beertracker.table.EstiloContract;

public class Estilo implements Parcelable{

    @SerializedName("cod_estilo")
    private long codEstilo;
    @SerializedName("nome_estilo")
    private String nomeEstilo;
    private String descricao;
    private double preco;
    private String imagem;

    public String getImagem() {
        return imagem;
    }

    public void setImagem(String imagem) {
        this.imagem = imagem;
    }

    public long getCodEstilo() {
        return codEstilo;
    }

    public void setCodEstilo(long codEstilo) {
        this.codEstilo = codEstilo;
    }

    public String getNomeEstilo() {
        return nomeEstilo;
    }

    public void setNomeEstilo(String nomeEstilo) {
        this.nomeEstilo = nomeEstilo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel out, int i) {
        out.writeLong(codEstilo);
        out.writeString(nomeEstilo);
        out.writeString(descricao);
        out.writeString(imagem);
        out.writeDouble(preco);
    }

    public Estilo(){}

    public Estilo(Cursor cursor){
        this.codEstilo = cursor.getLong(cursor.getColumnIndex(EstiloContract.EstiloEntry._ID));
        this.nomeEstilo = cursor.getString(cursor.getColumnIndex(EstiloContract.EstiloEntry.COLUMN_NAME));
        this.descricao = cursor.getString(cursor.getColumnIndex(EstiloContract.EstiloEntry.COLUMN_DESCRICAO));
        this.preco = cursor.getDouble(cursor.getColumnIndex(EstiloContract.EstiloEntry.COLUMN_PRECO));
        this.imagem = cursor.getString(cursor.getColumnIndex(EstiloContract.EstiloEntry.COLUMN_IMAGE));
    }

    public Estilo(Parcel in){
        codEstilo = in.readLong();
        nomeEstilo = in.readString();
        descricao = in.readString();
        imagem = in.readString();
        preco = in.readDouble();
    }

    public static final Parcelable.Creator<Estilo> CREATOR = new Parcelable.Creator<Estilo>(){

        @Override
        public Estilo createFromParcel(Parcel parcel) {
            return new Estilo(parcel);
        }

        @Override
        public Estilo[] newArray(int i) {
            return new Estilo[i];
        }
    };
}
