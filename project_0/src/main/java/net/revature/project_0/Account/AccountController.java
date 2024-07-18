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

    private final AccountService accountService;

    public AccountController(AccountService accountService){
        this.accountService=accountService;
    }

    //Legacy Call?
    public void addAccount(String email, String password, int userNum) throws InvalidInputException {


            Account addedAccount = new Account(email, password, userNum);

            try{
                accountService.create(addedAccount);
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
        app.get("/Account/showAll",this::getAllAccounts);
        app.get("/Account/", this::getAccountByID);
        app.post("/Account/Create",this::postCreateNewAccount);
    }

    private List<Account> getAllAccounts(Context context) {
        //TODO implement a check for user premision needed as ADMIN not endUser
        List<Account> accounts = accountService.lookup();
        context.json(accounts);
        return accounts;

    }

    //End User View Account
    private void getAccountByID(Context context) {

        String accountString ="";
        accountString+=context.header("user_number_ID");
        if(accountString.isEmpty()){
            //TODO add something that tells user not logged in rather than output to console
            context.json("Not Logged In\nCreate an account ");
            return;
        }
        int accountID = Integer.parseInt(accountString);
        Account foundAccount = accountService.findByID(accountID);
        context.json(foundAccount);

    }

    //Maybe use this as the admin method?
    private void postNewAccount(Context context) {
        Account account =  context.bodyAsClass(Account.class);
        context.json(accountService.create(account));
        context.status(HttpStatus.CREATED);
    }

    private void postCreateNewAccount(Context context) {
        String email, password;
        email =context.queryParam("email");
        password = context.queryParam("password");
        if(accountService.newUserCreateAccount(email,password)) {
            context.json("Account Created");
            context.status(HttpStatus.CREATED);
            }
        else{
            //System.out.println(email);
            //System.out.println(password);
            context.json("Error Email already in use or Email isn't formated correctly");
            context.status(HttpStatus.BAD_REQUEST);
        }
    }

}
