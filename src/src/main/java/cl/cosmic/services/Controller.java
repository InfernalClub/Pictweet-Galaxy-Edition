package cl.cosmic.services;

import cl.cosmic.model.Persona;
import cl.cosmic.model.Pic;  // Corrected to use cl.cosmic.model
import cl.cosmic.model.PicTwin;  // Corrected to use cl.cosmic.model
import com.password4j.Password;
import io.ebean.Database;
import io.ebean.annotation.Transactional;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Controller {

    /* The database. */
    private final Database database;

    /** The Constructor. */
    public Controller(@NonNull final Database database) {
        this.database = database;
    }

    /** Register a new user. */
    @Transactional
    public Persona register(@NonNull final String email, @NonNull final String password) {
        // Hash the password
        String hashedPassword = Password.hash(password).withBcrypt().getResult();
        log.debug("Hashed password: {}", hashedPassword);

        // Build the Persona
        Persona persona = Persona.builder()
                .email(email)
                .password(hashedPassword)
                .strikes(0)
                .blocked(false)
                .build();

        // Save the Persona
        this.database.save(persona);
        log.debug("Persona saved: {}", persona);

        return persona;
    }
}
