package net.revature.project_0.Bank;

import java.math.BigDecimal;

public class BankAccount {
    //Atributes
    //OOP -Encapsulation
    private BigDecimal balance;
    private int accountNumber;
    private int accountOwner;
    private int accountType;
    private String accountTypeName;

    public BankAccount(){}
    public BankAccount(int accountNumber, int accountOwner, BigDecimal balance, int accountType){
        this.accountNumber=accountNumber;
        this.accountOwner=accountOwner;
        this.balance=balance;
        this.accountType=accountType;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public int getAccountOwner() {
        return accountOwner;
    }

    public int getAccountType() {
        return accountType;
    }

    public String getAccountTypeName() {
        return accountTypeName;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public void setAccountNumber(int accountNumber) {
        this.accountNumber = accountNumber;
    }

    public void setAccountOwner(int accountOwner) {
        this.accountOwner = accountOwner;
    }

    public void setAccountType(int accountType) {
        this.accountType = accountType;
    }

    public void setAccountTypeName(String accountTypeName) {
        this.accountTypeName = accountTypeName;
    }
}
