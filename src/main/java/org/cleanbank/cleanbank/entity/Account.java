package org.cleanbank.cleanbank.entity;

import org.springframework.context.annotation.Profile;

import javax.persistence.*;

//@Profile("test")
@Entity
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "accountNumber")
    private int accountNumber;

    @Column(name = "holderName")
    private String accHolderName;

    @Column(name = "address")
    private String address;

    @Column(name = "accountBalance")
    private int accountBalance;

    @ManyToOne
    private Branch branch;

    //Constructors

    public Account(int accountNumber, String accHolderName, String address, int accountBalance, int branchCode) {
        this.accountNumber = accountNumber;
        this.accHolderName = accHolderName;
        this.address = address;
        this.accountBalance = accountBalance;
        //To Link branch with Account
        this.branch = new Branch(branchCode,"","");
    }

    public Account(int accountNumber, String accHolderName, String address) {
        this.accountNumber = accountNumber;
        this.accHolderName = accHolderName;
    }

    public Account() {
    }

    //GettersAndSetters

    public int getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(int accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getAccHolderName() {
        return accHolderName;
    }

    public void setAccHolderName(String accHolderName) {
        this.accHolderName = accHolderName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getAccountBalance() {
        return accountBalance;
    }

    public void setAccountBalance(int accountBalance) {
        this.accountBalance = accountBalance;
    }

    public Branch getBranch() {
        return branch;
    }

    public void setBranch(Branch branch) {
        this.branch = branch;
    }

    //toString

    @Override
    public String toString() {
        return "Account{" +
                "accountNumber=" + accountNumber +
                ", accHolderName='" + accHolderName + '\'' +
                ", address='" + address + '\'' +
                ", accountBalance=" + accountBalance +
                ", branch=" + branch +
                '}';
    }
}
