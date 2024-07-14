package net.revature.project_0.Bank;

import net.revature.project_0.util.interfaces.Serviceable;

import java.util.ArrayList;
import java.util.List;

public class BankAccountService implements Serviceable<BankAccount> {

    private BankRepository bankRepository;
    private List<BankAccount> listOfAccounts = new ArrayList<>();

    public BankAccountService(BankRepository bankRepository){
        this.bankRepository=bankRepository;
    }

    @Override
    public BankAccount lookup(List<BankAccount> databaseContents, int id) {
        return null;
    }

    @Override
    public BankAccount create(BankAccount newObject) {
        return null;
    }

    @Override
    public BankAccount findByID(int number) {
        return null;
    }
}
