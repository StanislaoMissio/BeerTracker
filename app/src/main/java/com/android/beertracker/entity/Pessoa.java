package com.android.beertracker.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Pessoa {

    @SerializedName("cod_pessoa")
    @Expose
    private int codPessoa;

    @SerializedName("usuario")
    @Expose
    private String usuario;

    @SerializedName("senha")
    @Expose
    private String senha;

    @SerializedName("email")
    @Expose
    private String email;

    public int getCodPessoa() {
        return codPessoa;
    }

    public void setCodPessoa(int codPessoa) {
        this.codPessoa = codPessoa;
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

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    @SerializedName("telefone")
    @Expose
    private String telefone;


}
