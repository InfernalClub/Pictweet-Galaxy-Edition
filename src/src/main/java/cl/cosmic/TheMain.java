package cl.cosmic;

import cl.cosmic.model.Persona; // Adjusted for your package
import cl.cosmic.services.Controller; // Adjusted for your package
import io.ebean.DB;
import lombok.extern.slf4j.Slf4j; // Corrected import for Slf4j

/** The Main. */
@Slf4j
public class TheMain {

    /** Starting point. */
    public static void main(String[] args) {
        log.debug("Starting TheMain ..");

        // Create the controller with the default database connection
        Controller controller = new Controller(DB.getDefault());

        // Register a new Persona
        Persona p1 = controller.register("durrutia@ucn.cl", "durrutia123");

        // Log the registered Persona
        log.debug("Persona: {}", p1);

        log.debug("Done.");
    }
}

