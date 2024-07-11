package net.revature.project_0.Account;

import net.revature.project_0.util.interfaces.Serviceable;

import java.util.ArrayList;
import java.util.List;

public class AccountService implements Serviceable<Account> {

    private List<Account> accountList = new ArrayList<>();

    @Override
    public List<Account> lookup() {
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


}
