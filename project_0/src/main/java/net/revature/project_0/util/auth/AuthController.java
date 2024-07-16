package net.revature.project_0.util.auth;

import io.javalin.Javalin;
import io.javalin.http.Context;
import net.revature.project_0.util.interfaces.Controller;

public class AuthController implements Controller {
   private final AuthService authService;

   public AuthController(AuthService authService){this.authService=authService;}

    @Override
    public void registerPaths(Javalin app) {
        app.post("/login",this::postLogin);
    }

    public void postLogin(Context context){
            String email = context.queryParam("email");
            String password = context.queryParam("password");

    }

}
