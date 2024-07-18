package net.revature.project_0.util;

import io.javalin.Javalin;
import net.revature.project_0.Account.AccountController;
import net.revature.project_0.Account.AccountRepository;
import net.revature.project_0.Account.AccountService;
import net.revature.project_0.Bank.BankAccountController;
import net.revature.project_0.Bank.BankAccountService;
import net.revature.project_0.Bank.BankRepository;
import net.revature.project_0.util.auth.AuthController;
import net.revature.project_0.util.auth.AuthService;

/**
 * Hello world!
 *
 */
public class Project_Runner
{
    public static void main( String[] args )
    {
        //Place to do declarations

        Javalin app = Javalin.create();
        //Create Banks runners
        BankRepository bankRepository = new BankRepository();
        BankAccountService bankAccountService = new BankAccountService(bankRepository);
        BankAccountController bankAccountController = new BankAccountController(bankAccountService);
        bankAccountController.registerPaths(app);
        //Create Account runners
        AccountRepository accountRepository = new AccountRepository();
        AccountService accountService = new AccountService(accountRepository);
        AccountController accountController = new AccountController(accountService);
        accountController.registerPaths(app);
        //Auth things
        AuthService authService = new AuthService(accountService);
        AuthController authController = new AuthController(authService);
        authController.registerPaths(app);

        app.start(8080);
    }
}
