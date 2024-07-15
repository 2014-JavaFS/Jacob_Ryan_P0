package net.revature.project_0.Bank;

import java.util.List;
import java.util.Scanner;

public class BankAccountController {
    private final BankAccountService bankAccountService;
    private final Scanner scanner;

    public BankAccountController(Scanner scanner,BankAccountService bankAccountService){
        this.bankAccountService=bankAccountService;
        this.scanner=scanner;
    }

    //TODO: Implement adding account to the DB, after Passing info for validation
    public void addAccount(){


    }
    //TODO: Implement modifying an account on the DB, after Passing info for validation
    public void deposit(int accountNumber, int depositedMoney){


    }
    //TODO: Implement modifying an account on the DB, after Passing info for validation
    public void withdrawal(int accountNumber, int amountRequested){


    }
    //TODO: Implement searching for all records associated with an account on the DB, and then putting it in a readable format
    public void showAccounts(){


    }

    public List<BankAccount> getAllUserAccounts(int UserIDNumber) {
       return null;
    }



}
