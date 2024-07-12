package net.revature.project_0.Account;

import net.revature.project_0.util.exceptions.InvalidInputException;

import java.util.Scanner;

public class AccountController {
    private final Scanner scanner;

    private final AccountService accountService;
    private int count =0;

    public AccountController(Scanner scanner, AccountService accountService){
        this.scanner=scanner;
        this.accountService=accountService;
    }

    public void addAccount(String email, String password, int userNum) throws InvalidInputException {


            Account addedAccount = new Account(email, password, userNum);

            try{
                accountService.addAccount(addedAccount);
            } catch(InvalidInputException e){
                e.printStackTrace();
                System.out.println(e.getMessage());
            } catch(RuntimeException e){
                e.printStackTrace();
                   System.out.println("RuntimeException Found");
                }
    }
}
