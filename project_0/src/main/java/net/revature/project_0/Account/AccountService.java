package net.revature.project_0.Account;

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
    public Account lookup(List<Account> databaseContents, int I) {
        return null;
    }

    @Override
    public Account create(Account newObject) {
        accountList.add(newObject);
        return newObject;
    }

    @Override
    public Account findByID(int number) {
        for(Account account:accountList){
            if( account.getUserNumID()==number)
                return account;
        }
        return null;
    }

    public Account addAccount(Account addedAccount) throws InvalidInputException{
        //TODO:Put in Input Validation like Password has x length or email ends in .com/has a @ symbol in it
        return accountRepository.addAccount(addedAccount);
    }


}
