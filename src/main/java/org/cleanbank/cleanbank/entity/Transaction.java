package org.cleanbank.cleanbank.entity;

import org.springframework.context.annotation.Profile;

import javax.persistence.*;

@Profile("test")
@Entity
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "transactionId")
    private int transactionId;

    @Column(name = "transactionType")
    private String transactionType;

    @Column(name = "amount")
    private int amount;

    @ManyToOne
    private Account account;

    //Constructors
    public Transaction(int transactionId, String transactionType, int amount, int accountNumber) {
        this.transactionId = transactionId;
        this.transactionType = transactionType;
        this.amount = amount;
        this.account = new Account(accountNumber,"","");
    }

    public Transaction() {
    }

    //GettersAndSetters

    public int getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(int transactionId) {
        this.transactionId = transactionId;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public Account getAccount() {
        return account;
    }

    public Account setAccountEntity(Account account) {
        this.account = account;
        return account;
    }

    //toString

    @Override
    public String toString() {
        return "Transaction{" +
                "transactionId=" + transactionId +
                ", transactionType='" + transactionType + '\'' +
                ", amount=" + amount +
                ", account=" + account +
                '}';
    }
}
