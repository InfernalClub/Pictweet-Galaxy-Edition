package cl.cosmic.web;
import io.javalin.http.Handler;

import lombok.Getter;
import lombok.NonNull;

/**
 * Abstract class representing an HTTP route.
 */
@Getter
public abstract class Route {

    /** The HTTP method for the route (GET, POST, PUT, etc.). */
    protected Method method;

    /** The URL path for the route. */
    protected String path;

    /** The handler to process requests for this route. */
    protected Handler handler;

    /**
     * Constructor to initialize the Route.
     *
     * @param method the HTTP method.
     * @param path   the URL path.
     */
    protected Route(@NonNull final Method method, @NonNull final String path) {
        this.method = method;
        this.path = path;
    }

    /**
     * Enum representing HTTP methods.
     */
    public enum Method {
        GET, // For retrieving resources
        POST, // For creating resources
        PUT, // For updating resources
    }
}

