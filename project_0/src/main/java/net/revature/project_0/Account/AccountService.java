package net.revature.project_0.Account;

import net.revature.project_0.Bank.BankAccount;
import net.revature.project_0.util.exceptions.DataNotFoundException;
import net.revature.project_0.util.exceptions.InvalidInputException;
import net.revature.project_0.util.interfaces.Serviceable;

import java.util.ArrayList;
import java.util.List;

public class AccountService implements Serviceable<Account> {

    private List<Account> accountList = new ArrayList<>();
    private AccountRepository accountRepository;

    public  AccountService(AccountRepository accountRepository){
        this.accountRepository = accountRepository;
    }

    @Override
    public List<Account> lookup() {
        List<Account> accounts = accountRepository.lookup();
        if(accounts.isEmpty())
            throw new DataNotFoundException("No Accounts in Database");
        else
            return accounts;
    }

    @Override
    public Account create(Account newObject) throws InvalidInputException {
        accountRepository.create(newObject);
        return newObject;
    }

    @Override
    public Account findByID(int number) {
        return accountRepository.findByID(number);
    }

    //TODO: UPDATE FEATURE????


    //TODO: Either change to Bank Account Associated Lookup table, or remove
    public List<Account> findAllAccounts(){
        List<Account> accounts = accountRepository.lookup();
        return accounts;
    }

    //TODO: Either make this the Linked Bank Account Creation Tool or Remove
    public BankAccount addAccount(Account addedAccount) throws InvalidInputException{
        return accountRepository.addAccount(addedAccount);
    }


}
