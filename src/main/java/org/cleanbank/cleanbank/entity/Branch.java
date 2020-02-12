package org.cleanbank.cleanbank.entity;

import org.springframework.context.annotation.Profile;

import javax.persistence.*;
@Profile("test")
@Entity
public class Branch {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "branchCode")
    private int branchCode;

    @Column(name = "branchName")
    private String branchName;

    @Column(name = "branchCity")
    private String branchCity;

    //Constructors

    public Branch(int branchCode, String branchName, String branchCity) {
        this.branchCode = branchCode;
        this.branchName = branchName;
        this.branchCity = branchCity;
    }

    public Branch() {
    }

    //GettersAndSetters

    public int getBranchCode() {
        return branchCode;
    }

    public void setBranchCode(int branchCode) {
        this.branchCode = branchCode;
    }

    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    public String getBranchCity() {
        return branchCity;
    }

    public void setBranchCity(String branchCity) {
        this.branchCity = branchCity;
    }

    //toString

    @Override
    public String toString() {
        return "Branch{" +
                "branchCode=" + branchCode +
                ", branchName='" + branchName + '\'' +
                ", branchCity='" + branchCity + '\'' +
                '}';
    }
}
