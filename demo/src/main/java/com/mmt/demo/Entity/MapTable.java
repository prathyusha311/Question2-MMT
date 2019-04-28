package com.mmt.demo.Entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.UUID;

@Entity
public class MapTable {

    @Id
    @Column
    String name;

    @Column
    String gender;

    @Column
    Timestamp createdOn;

    @Column
    int expiryTime;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Timestamp getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Timestamp createdOn) {
        this.createdOn = createdOn;
    }

    public int getExpiryTime() {
        return expiryTime;
    }

    public void setExpiryTime(int expiryTime) {
        this.expiryTime = expiryTime;
    }

    /*@PrePersist
    public void prePersist(){
        name = UUID.randomUUID().toString();
    }*/
}
