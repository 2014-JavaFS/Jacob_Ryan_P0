package net.revature.project_0.Account;

import net.revature.project_0.Bank.BankAccount;
import net.revature.project_0.util.exceptions.DataNotFoundException;
import net.revature.project_0.util.exceptions.InvalidInputException;
import net.revature.project_0.util.interfaces.Serviceable;

import java.util.ArrayList;
import java.util.List;

public class AccountService implements Serviceable<Account> {

    private AccountRepository accountRepository;

    public  AccountService(AccountRepository accountRepository){
        this.accountRepository = accountRepository;
    }

    @Override
    public List<Account> lookup() {
        List<Account> accounts = accountRepository.lookup();
        if(accounts.isEmpty())
            throw new DataNotFoundException("No Accounts in Database");
        else
            return accounts;
    }

    @Override
    public Account create(Account newObject) throws InvalidInputException {
        accountRepository.create(newObject);
        return newObject;
    }

    @Override
    public Account findByID(int number) {
        return accountRepository.findByID(number);
    }

    public boolean checkIfEmailIsInUse(String Email){
        Account checkingAccountExistence = accountRepository.findByEmail(Email);
        //System.out.println("Checking Progress 4");
        if(checkingAccountExistence==null){return true;}

        return false;
    }

    public boolean newUserCreateAccount(String userName, String password){
        //TODO: Need to check that Email is a legit email ie Has @ symbol and ends with a . something
        //Checks that Email is an actual email
        //System.out.println("Checking Progress 1");
        if(password.isEmpty() || userName.isEmpty())
            return false;
        //System.out.println(userName);
        //System.out.println(userName.charAt(userName.length()-4));
        //System.out.println(userName.charAt(userName.length()-3));
        if(userName.charAt(userName.length()-4)=='.' || userName.charAt(userName.length()-3)=='.'){

        }
        else {
            return false;
        }
        //System.out.println("Checking Progress 2");
        if(!userName.contains("@")){
            return false;
        }
        //System.out.println("Checking Progress 3");
        //Checks the DB to see if the Email already exists
        boolean checkingEmailUsage;
        try{
            checkingEmailUsage=true;
            checkIfEmailIsInUse(userName);
        }catch (DataNotFoundException e){
           checkingEmailUsage=false;
        }
        if(checkingEmailUsage) {
            //If it already Exists
            return false;
            }
        else
            {
                //System.out.println("Checking Progress 5");
                Account gettingUserIDNumber = new Account();
                int userIDNumber=(int)(Math.random()*1000000);
                boolean foundAccountNumber = false;
                while(!foundAccountNumber) {
                    try {
                        gettingUserIDNumber = accountRepository.findByID(userIDNumber);
                    } catch (DataNotFoundException e) {
                        foundAccountNumber=true;
                        gettingUserIDNumber.setUserNumID(userIDNumber);
                        gettingUserIDNumber.setUserName(userName);
                        gettingUserIDNumber.setPassword(password);
                        accountRepository.create(gettingUserIDNumber);
                    }
                }

                return true;
            }
    }

    public Account findByEmailAndPassword(String email, String password){
        return accountRepository.findByEmailAndPassword(email, password);
    }

    //TODO: Implement the additional TODOs from Account Controller

}
