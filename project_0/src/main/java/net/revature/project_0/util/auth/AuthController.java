package net.revature.project_0.util.auth;

import io.javalin.Javalin;
import io.javalin.http.Context;
import io.javalin.http.HttpStatus;
import net.revature.project_0.Account.Account;
import net.revature.project_0.util.interfaces.Controller;

import javax.security.sasl.AuthenticationException;

public class AuthController implements Controller {
   private final AuthService authService;

   public AuthController(AuthService authService){this.authService=authService;}

    @Override
    public void registerPaths(Javalin app) {
        app.post("/login",this::postLogin);
        app.get("/user-info", this::getRedirect);
    }

    //Not sure if I'm going to be using this
    //TODO: Figure out if this redirect is needed to be implemented
    private void getRedirect(Context context){}

    private void postLogin(Context context){
            String email = context.queryParam("email");
            String password = context.queryParam("password");

            try{
                Account account = authService.login(email,password);
                context.header("user_number_ID",String.valueOf(account.getUserNumID()));
                context.header("account_privilege",account.getAccountType().name());
                context.status(200);
            }catch(AuthenticationException e){
                context.status(HttpStatus.UNAUTHORIZED);
            }
    }

}
