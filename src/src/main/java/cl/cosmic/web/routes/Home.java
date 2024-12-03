package cl.cosmic.web.routes;
import cl.cosmic.web.Route;

import io.javalin.http.Context;
import io.javalin.http.Handler;

/**
 * The Home route.
 * This route handles the root path ("/") of the application.
 */
public final class Home extends Route {

    /**
     * Constructor for the Home route.
     */
    public Home() {
        // Define the HTTP method as GET and the path as "/"
        super(Method.GET, "/");

        // Assign the handler for this route
        this.handler = ctx -> {
            // Respond with a welcome message
            ctx.result("Welcome to PicTwin");
        };
    }
}

