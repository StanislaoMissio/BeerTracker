package com.android.beertracker.model;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class Role {

    @SerializedName("cod_role")
    @Expose
    private int codRole;

    @SerializedName("nome_role")
    @Expose
    private String nomeRole;

    @SerializedName("autor_role")
    @Expose
    private String autorRole;

    @SerializedName("local_role")
    @Expose
    private String localRole;

    @SerializedName("data_role")
    @Expose
    private Date dataRole;

    public int getCodRole() {
        return codRole;
    }

    public void setCodRole(int codRole) {
        this.codRole = codRole;
    }

    public String getNomeRole() {
        return nomeRole;
    }

    public void setNomeRole(String nomeRole) {
        this.nomeRole = nomeRole;
    }

    public String getAutorRole() {
        return autorRole;
    }

    public void setAutorRole(String autorRole) {
        this.autorRole = autorRole;
    }

    public String getLocalRole() {
        return localRole;
    }

    public void setLocalRole(String localRole) {
        this.localRole = localRole;
    }

    public Date getDataRole() {
        return dataRole;
    }

    public void setDataRole(Date dataRole) {
        this.dataRole = dataRole;
    }
}
