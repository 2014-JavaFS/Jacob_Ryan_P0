package net.revature.project_0.util.auth;

import net.revature.project_0.Account.Account;
import net.revature.project_0.Account.AccountService;

import javax.security.sasl.AuthenticationException;

public class AuthService {
    private final AccountService accountService;

    public AuthService(AccountService accountService){this.accountService=accountService;}


    public Account login(String email, String password) throws javax.security.sasl.AuthenticationException {

         Account account = accountService.findByEmailAndPassword(email, password);
         if(account == null)
             throw new AuthenticationException("Invalid member Credentials, please try again");

         return account;
    }
}
