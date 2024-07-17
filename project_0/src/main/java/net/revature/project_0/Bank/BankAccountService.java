package net.revature.project_0.Bank;

import net.revature.project_0.util.exceptions.DataNotFoundException;
import net.revature.project_0.util.exceptions.InvalidInputException;
import net.revature.project_0.util.interfaces.Serviceable;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class BankAccountService implements Serviceable<BankAccount> {

    private BankRepository bankRepository;

    public BankAccountService(BankRepository bankRepository){
        this.bankRepository=bankRepository;
    }

    //This returns all Bank Accounts that the Account Holder has with the bank
    @Override
    public List<BankAccount> lookup() {
        List<BankAccount> dbContents = bankRepository.lookup();

        //probably should return a data not found exception
        return dbContents;
    }

    @Override
    public BankAccount create(BankAccount newBankAccount) {
        return bankRepository.create(newBankAccount);

    }

    //This returns the account from number given
    @Override
    public BankAccount findByID(int number) throws DataNotFoundException {
        return bankRepository.findByID(number);
    }

    public List<BankAccount> getAssociatedAccounts(int idNum){

            return bankRepository.findAssociatedAccounts(idNum);
    }

    //TODO:Admin Deposit Function Need to Check that Person Calling this Method is an Admin
    public BankAccount putDeposit(BigDecimal selectedAccount, int accountNumber, int accountID){


        BigDecimal toCompare = BigDecimal.valueOf(0);
        if(selectedAccount.compareTo(toCompare) < 0)
            throw new InvalidInputException("Deposit Amount Must Not Be Negative");

        return bankRepository.deposit(selectedAccount, accountNumber, accountID);
    }

    //TODO:Placeholder for Admin Withdrawl Method, Should Implement
    public BankAccount putWithdrawal(){
        return null;
    }

    //End User Create Account
    public boolean openNewAccount(int userID){
        //TODO:Read Bellow and Implement for MVP
        //userID is the Account Owner, Need to find a valid Account Number that isn't in use
        //Look at AccountService newUserCreateAccount for reference
        //Create a regular Bank Account and fill it with the data, then Call Create with the
        //Filled in Bank Account Object


        return true;
    }

    //End User Deposit into Account
    //TODO:Implement


    //End User Withdrawal from Account
    //TODO:Implement




}
