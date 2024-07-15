package net.revature.project_0.Bank;

import net.revature.project_0.util.exceptions.DataNotFoundException;
import net.revature.project_0.util.interfaces.Serviceable;

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


        //probably should return a data not found exception
        return null;
    }

    @Override
    public BankAccount create(BankAccount newBankAccount) {
        //TODO: Need to check that the Account's ID that has been given actually exists

        //TODO: Need to check that the Bank Account Number Doesn't already exist

        //TODO: Then I need to actually add it to the DB before returning the BankAccount

        return newBankAccount;
    }

    //This returns the account from number given
    @Override
    public BankAccount findByID(int number) throws DataNotFoundException {
        return bankRepository.findByID(number);
    }
}
