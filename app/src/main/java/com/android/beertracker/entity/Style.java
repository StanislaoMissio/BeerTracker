package com.android.beertracker.entity;


import com.google.gson.annotations.SerializedName;

import android.database.Cursor;
import android.os.Parcel;
import android.os.Parcelable;

import com.android.beertracker.table.EstiloContract;

public class Style implements Parcelable{

    @SerializedName("cod_estilo")
    private long codEstilo;
    @SerializedName("nome_estilo")
    private String nomeEstilo;
    private String descricao;
    private double preco;
    private String image;

    public String getImagem() {
        return image;
    }

    public void setImagem(String image) {
        this.image = image;
    }

    public long getCodStyle() {
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
        out.writeString(image);
        out.writeDouble(preco);
    }

    public Style(){}

    public Style(Cursor cursor){
        this.codEstilo = cursor.getLong(cursor.getColumnIndex(EstiloContract.EstiloEntry._ID));
        this.nomeEstilo = cursor.getString(cursor.getColumnIndex(EstiloContract.EstiloEntry.COLUMN_NAME));
        this.descricao = cursor.getString(cursor.getColumnIndex(EstiloContract.EstiloEntry.COLUMN_DESCRICAO));
        this.preco = cursor.getDouble(cursor.getColumnIndex(EstiloContract.EstiloEntry.COLUMN_PRECO));
        this.image = cursor.getString(cursor.getColumnIndex(EstiloContract.EstiloEntry.COLUMN_IMAGE));
    }

    public Style(Parcel in){
        codEstilo = in.readLong();
        nomeEstilo = in.readString();
        descricao = in.readString();
        image = in.readString();
        preco = in.readDouble();
    }

    public static final Parcelable.Creator<Style> CREATOR = new Parcelable.Creator<Style>(){

        @Override
        public Style createFromParcel(Parcel parcel) {
            return new Style(parcel);
        }

        @Override
        public Style[] newArray(int i) {
            return new Style[i];
        }
    };
}
