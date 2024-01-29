package com.example.shopp;

import java.util.Date;

public class FinancierData extends RegisterData {

    private Integer id;
    private String financierID;
    private String username;
    private Date dateInsert;
    private Date dateUpdate;
    private Date dateDelete;

    public FinancierData(Integer id, String financierID, String username,
                         Date dateInsert, Date dateUpdate, Date dateDelete) {

        this.id = id;
        this.financierID = financierID;
        this.username = username;
        this.dateInsert = dateInsert;
        this.dateUpdate = dateUpdate;
        this.dateDelete = dateDelete;
    }

    public String getFinancierID() {
        return financierID;
    }

    public String getUsername() {
        return username;
    }

    public Date getDateInsert() {
        return dateInsert;
    }

    public Date getDateUpdate() {
        return dateUpdate;
    }

    public Date getDateDelete() {
        return dateDelete;
    }

    public Integer getId() {
        return id;
    }
}
