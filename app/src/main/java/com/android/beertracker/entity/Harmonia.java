package com.android.beertracker.entity;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Harmonia {

    @SerializedName("nome_harmonia")
    @Expose
    private String nomeHarmonia;

    @SerializedName("descricao")
    @Expose
    private String descricao;

    @SerializedName("cod_harmonia")
    @Expose
    private int codHarmonia;

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
}
