package net.revature.project_0.Bank;

import net.revature.project_0.util.exceptions.DataNotFoundException;
import net.revature.project_0.util.exceptions.InvalidInputException;
import net.revature.project_0.util.interfaces.Serviceable;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class BankAccountService implements Serviceable<BankAccount> {

    private BankRepository bankRepository;
    private List<BankAccount> listOfAccounts = new ArrayList<>();

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
        //Should probably check that the ID is the same as the one that is logged in
        //Though I need to make the Login Feature to be able to do that

        if(true) {
            return bankRepository.findAssociatedAccounts(idNum);
            }
        else {
            return null;
            }

    }

    //TODO:Placeholder
    public BankAccount putDeposit(BigDecimal selectedAccount, int accountNumber, int accountID){

        //TODO Once Login is made, check that everything is good

        BigDecimal toCompare = BigDecimal.valueOf(0);
        if(selectedAccount.compareTo(toCompare) < 0)
            throw new InvalidInputException("Deposit Amount Must Not Be Negative");

        return bankRepository.deposit(selectedAccount, accountNumber, accountID);
    }

    //TODO:Placeholder
    public BankAccount putWithdrawal(){
        return null;
    }

}
