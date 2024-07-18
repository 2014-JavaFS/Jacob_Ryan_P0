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
        //Create a regular Bank Account and fill it with the data, then Call Create with the
        //Filled in Bank Account Object
        BankAccount newlyOpenedAccount = new BankAccount();
        newlyOpenedAccount.setAccountOwner(userID);

        //Finding an unused account number
        int creatingNewAccountNumber = (int)(Math.random()*10000000);
        boolean looking = true;
        while(looking) {
            try {
                findByID(creatingNewAccountNumber);
            } catch (DataNotFoundException e) {
                newlyOpenedAccount.setAccountNumber(creatingNewAccountNumber);
                looking=false;
            }
        }

        bankRepository.create(newlyOpenedAccount);
        return true;
    }

    public BankAccount putEndUserDeposit(BigDecimal depositAmount, int accountNum, int ID) {

        BankAccount toCheck = new BankAccount();
        //Check that Account Exists, and if it does that the User IDs match
        try {
            toCheck = findByID(accountNum);
            }catch (DataNotFoundException e){
            e.printStackTrace();
            return null;
            }
        if(toCheck.getAccountOwner()!=ID){
            return null;
        }

        //Function That we pass to make the deposit also
        // does check that the Deposit Amount isn't negative
        return putDeposit(depositAmount, accountNum, ID);
    }

    public BankAccount putEndUserWithdrawal(BigDecimal withDrawalAmount, int accountNum, int ID) {

        BankAccount toCheck = new BankAccount();
        //Check that Account Exists, and if it does that the User IDs match
        //System.out.println("checking Progress 1");
        try {
            toCheck = findByID(accountNum);
        }catch (DataNotFoundException e){
            e.printStackTrace();
            return null;
        }
        //System.out.println("checking Progress 3");
        if(toCheck.getAccountOwner()!=ID){
            return null;
        }
        BigDecimal toCompare = BigDecimal.valueOf(0);
        if(withDrawalAmount.compareTo(toCompare) < 0)
            return null;
        //System.out.println("checking Progress 4");
        //System.out.println(withDrawalAmount);
        //System.out.println(toCheck.getBalance());
        //System.out.println(withDrawalAmount.compareTo(toCheck.getBalance()));
        //Makes sure that the funds in the account are enough to not overdraft
        if(withDrawalAmount.compareTo(toCheck.getBalance())==-1)
            return bankRepository.withdrawal(toCheck, withDrawalAmount);
        else
            return null;

        //



    }

    //End User Deposit into Account



    //End User Withdrawal from Account





}
