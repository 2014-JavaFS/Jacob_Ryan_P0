package net.revature.project_0.Bank;

import io.javalin.Javalin;
import io.javalin.http.Context;
import io.javalin.http.HttpStatus;
import net.revature.project_0.util.interfaces.Controller;

import java.math.BigDecimal;
import java.util.List;
import java.util.Scanner;

public class BankAccountController implements Controller {
    private final BankAccountService bankAccountService;
    private final Scanner scanner;

    public BankAccountController(Scanner scanner,BankAccountService bankAccountService){
        this.bankAccountService=bankAccountService;
        this.scanner=scanner;
    }

    @Override
    public void registerPaths(Javalin app) {

    //For Testing/Presentation/Admin usage
        app.post("/Account/{accountID}/createBankAccount",this::addAccount);
        app.get("/Account/{accountID}/Holdings",this::showAccounts);
        app.get("/Account/Show_All_Bank_Accounts",this::getAllUserAccounts);
        app.get("/Account/{accountID}/{accountNumber}",this::getAccountFromID);
        app.put("/Account/{accountID}/{accountNumber}/Deposit",this::deposit);
        app.put("/Account/{accountID}/{accountNumber}/Withdrawal",this::withdrawal);


    //The Actual End User Versions
        app.get("/Account/Holdings",this::getAccountFromEnviorment);
        app.put("/Account/Holdings/Withdrawal",this::putWithdrawal);
        app.put("/Account/Holdings/Deposit",this::putDeposit);

    }


    public void addAccount(Context context){
        BankAccount bankAccount = context.bodyAsClass(BankAccount.class);
        context.json(bankAccountService.create(bankAccount));
        context.status(HttpStatus.CREATED);

    }

    //TODO: Implement modifying an account on the DB, after Passing info for validation
    public void deposit(Context context){

        int accountID = Integer.parseInt(context.pathParam("accountID"));
        int accountNum = Integer.parseInt(context.pathParam("accountNumber"));
        BigDecimal depositAmount = context.bodyAsClass(BigDecimal.class);
        context.json(bankAccountService.putDeposit(depositAmount, accountNum, accountID));
        //Not sure if this is what I could be doing here but I'll try it
        context.status(HttpStatus.ACCEPTED);
    }
    //TODO: Implement Admin
    public void withdrawal(Context context){


    }


    public List<BankAccount> showAccounts(Context context){
        int accountID = Integer.parseInt(context.pathParam("accountID"));
        List<BankAccount> dbContents = bankAccountService.getAssociatedAccounts(accountID);
        context.json(dbContents);
        return dbContents;
    }

    public List<BankAccount> getAllUserAccounts(Context context) {
        List<BankAccount> dbContents = bankAccountService.lookup();
        context.json(dbContents);
        return dbContents;
    }

    private void getAccountFromID(Context context){
        int accountID = Integer.parseInt(context.pathParam("accountID"));
        int accountNum = Integer.parseInt(context.pathParam("accountNumber"));
        BankAccount foundAccount = bankAccountService.findByID(accountNum);
        context.json(foundAccount);
    }

    //@TODO Make the End User Section Work for MVP

    //End User View
    private void getAccountFromEnviorment(Context context){


    }

    //End User Deposit
    private void putDeposit(Context context){

        int accountID = Integer.parseInt(context.pathParam("accountID"));
        int accountNum = Integer.parseInt(context.pathParam("accountNumber"));
        BigDecimal depositAmount = context.bodyAsClass(BigDecimal.class);
        context.json(bankAccountService.putDeposit(depositAmount, accountNum, accountID));
        //Not sure if this is what I could be doing here but I'll try it
        context.status(HttpStatus.ACCEPTED);
    }

    //End User Withdrawal
    private void putWithdrawal(Context context){}


    //End User Create New Account

}
