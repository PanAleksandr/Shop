package com.example.shopp;
import java.util.Date;

public class RegisterData {

    private Integer id;
    private String registerID;
    private String username;
    private Date birthDate;
    private Date dateInsert;
    private Date dateUpdate;
    private Date dateDelete;
    private String fullname;

    public RegisterData(/*,String subject*/){
        this.fullname =fullname;
        this.id= id;
        this.registerID = registerID;
        this.username = username;
        this.birthDate= birthDate;
        this.dateInsert= dateInsert;
        this.dateUpdate = dateUpdate;
        this.dateDelete = dateDelete;
    }

    public RegisterData(Integer id, String registerID){
        this.id=id;
        this.registerID=registerID;
    }

    public RegisterData(Integer id, String registerID, String username,String fullname, Date dateInsert){
        this.id= id;
        this.registerID= registerID;
        this.username =username;
        this.dateInsert=dateInsert;
        this.fullname =fullname;
    }

    public String getFullname(){
        return fullname;
    }
    public Integer getId(){
        return id ;
    }

    public String getRegisterID(){
        return registerID;
    }

    public String getUsername(){
        return username;
    }

    public Date getBirthDate(){
        return birthDate;
    }

    public Date getDateInsert(){
        return dateInsert;
    }

    public Date getDateUpdate(){
        return dateUpdate;
    }

    public Date getDateDelete(){
        return dateDelete;
    }


}
