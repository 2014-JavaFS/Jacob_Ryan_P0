package net.revature.project_0.Account;

import java.util.Scanner;

public class AccountController {
    private final Scanner scanner;
    private final AccountService accountService;
    private int count =0;

    public AccountController(Scanner scanner, AccountService accountService){
        this.scanner=scanner;
        this.accountService=accountService;
    }


}
