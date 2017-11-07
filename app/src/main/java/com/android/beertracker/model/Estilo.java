package com.android.beertracker.model;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Estilo {

    @SerializedName("cod_estilo")
    @Expose
    private int codEstilo;

    @SerializedName("nome_estilo")
    @Expose
    private String nomeEstilo;

    @SerializedName("descricao")
    @Expose
    private String descricao;

    @SerializedName("preco")
    @Expose
    private float preco;

    public int getCodEstilo() {
        return codEstilo;
    }

    public void setCodEstilo(int codEstilo) {
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

    public float getPreco() {
        return preco;
    }

    public void setPreco(float preco) {
        this.preco = preco;
    }
}
