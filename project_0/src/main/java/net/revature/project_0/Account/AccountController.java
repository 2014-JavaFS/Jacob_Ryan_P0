package net.revature.project_0.Account;

import io.javalin.Javalin;
import io.javalin.http.Context;
import io.javalin.http.HttpStatus;
import net.revature.project_0.Bank.BankAccount;
import net.revature.project_0.util.exceptions.InvalidInputException;
import net.revature.project_0.util.interfaces.Controller;

import java.util.List;
import java.util.Scanner;

public class AccountController implements Controller {
    private final Scanner scanner;

    private final AccountService accountService;
    private int count =0;

    public AccountController(Scanner scanner, AccountService accountService){
        this.scanner=scanner;
        this.accountService=accountService;
    }

    //Legacy Call?
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


    @Override
    public void registerPaths(Javalin app) {
        //app.get("/Account", this::getAllAccounts);
        app.post("/Account",this::postNewAccount);
        app.get("/Account",this::getAllAccounts);
        app.get("/Account/{accountID}", this::getAccountByID);
    }

    private List<Account> getAllAccounts(Context context) {
        List<Account> accounts = accountService.lookup();
        context.json(accounts);
        return accounts;

    }

    private void getAccountByID(Context context) {
        int accountID = Integer.parseInt(context.pathParam("accountID"));
        Account foundAccount = accountService.findByID(accountID);
        context.json(foundAccount);

    }

    private void postNewAccount(Context context) {
        Account account =  context.bodyAsClass(Account.class);
        context.json(accountService.create(account));
        context.status(HttpStatus.CREATED);
    }


}
