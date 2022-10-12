package org.cleanbank.cleanbank.entity;

import org.springframework.context.annotation.Profile;

import javax.persistence.*;

//@Profile("test")
@Entity
public class FixedDeposite {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "fdNumber")
    private int fdNumber;

    @Column(name = "fdAmount")
    private int fdAmount;

    @Column(name = "fdInterstRate")
    private int fdInterestRate;

    @Column(name = "fdDuration")
    private int fdDurations;

    @ManyToOne
    private Account account;

    //Constructors
    public FixedDeposite(int fdNumber, int fdAmount, int fdInterestRate, int fdDurations, int accountNumber) {
        this.fdNumber = fdNumber;
        this.fdAmount = fdAmount;
        this.fdInterestRate = fdInterestRate;
        this.fdDurations = fdDurations;
        //To Link AccountNumber into Fixed Deposit
        this.account = new Account(accountNumber,"","");
    }

    public FixedDeposite() {
    }

    //GettersAndSetters

    public int getFdNumber() {
        return fdNumber;
    }

    public void setFdNumber(int fdNumber) {
        this.fdNumber = fdNumber;
    }

    public int getFdAmount() {
        return fdAmount;
    }

    public void setFdAmount(int fdAmount) {
        this.fdAmount = fdAmount;
    }

    public int getFdInterestRate() {
        return fdInterestRate;
    }

    public void setFdInterestRate(int fdInterestRate) {
        this.fdInterestRate = fdInterestRate;
    }

    public int getFdDurations() {
        return fdDurations;
    }

    public void setFdDurations(int fdDurations) {
        this.fdDurations = fdDurations;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    //toString

    @Override
    public String toString() {
        return "FixedDeposite{" +
                "fdNumber=" + fdNumber +
                ", fdAmount=" + fdAmount +
                ", fdInterestRate=" + fdInterestRate +
                ", fdDurations=" + fdDurations +
                ", account=" + account +
                '}';
    }
}
