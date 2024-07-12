package net.revature.project_0;

import net.revature.project_0.Account.AccountController;
import net.revature.project_0.Account.AccountRepository;
import net.revature.project_0.Account.AccountService;
import net.revature.project_0.Bank.BankAccountController;
import net.revature.project_0.Bank.BankAccountService;
import net.revature.project_0.Bank.BankRepository;

import java.util.Scanner;

/**
 * Hello world!
 *
 */
public class Project_Runner
{
    public static void main( String[] args )
    {
        //Place to do declarations
        boolean userSessionActive=true;     //Controls User Loop ie program running or not
        int userChoice=0;
        Scanner scanner = new Scanner(System.in);
        //Create Banks runners
        BankRepository bankRepository = new BankRepository();
        BankAccountService bankAccountService = new BankAccountService();
        //BankAccountController bankAccountController = new BankAccountController(scanner, bankAccountService);
        //Create Account runners
        AccountRepository accountRepository = new AccountRepository();
        AccountService accountService = new AccountService(accountRepository);
        AccountController accountController = new AccountController(scanner, accountService);
        //Auth things?



        do{
            //Inside of here should prompt the User to Login, then be able to go through the various options
            //Probably will be replaced by some HTML UI later if we get to that

            System.out.println( "Welcome to Online Bank 343" );
            userSessionActive=false;

            //Temp DB checking stuff
            System.out.println( "Testing database insert method" );
            System.out.println( "Please enter Email" );
            String tempEmail =scanner.nextLine();
            System.out.println( "Please enter Password" );
            String tempPassword = scanner.nextLine();
            //Needs to be changed after each entry
            int tempUserID=9;
            accountController.addAccount(tempEmail,tempPassword,tempUserID);



        }while(userSessionActive);
    }
}
