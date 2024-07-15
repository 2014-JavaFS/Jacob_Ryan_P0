package net.revature.project_0.util.interfaces;

import io.javalin.Javalin;

public interface Controller {
    void registerPaths(Javalin app);
}
