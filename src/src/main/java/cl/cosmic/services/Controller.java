package cl.cosmic.services;

import cl.cosmic.model.Persona;
import cl.cosmic.model.Pic;
import cl.cosmic.model.PicTwin;
import cl.cosmic.model.query.QPersona;
import cl.cosmic.model.query.QPicTwin;
import cl.cosmic.utils.FileUtils;  // Assuming you have a utility class for file operations
import com.password4j.Password;
import io.ebean.Database;
import io.ebean.annotation.Transactional;
import java.io.File;
import java.time.Instant;
import java.util.List;
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

    /** The seed of the database. */
    public Boolean seed() {
        // Find the Persona count
        int personaCount = new QPersona().findCount();
        log.debug("Personas in database: {}", personaCount);

        // If Persona exists, don't seed
        if (personaCount > 0) {
            return Boolean.FALSE;
        }

        // Seed the database
        Persona persona = this.register("durrutia@ucn.cl", "durrutia123");
        log.debug("Persona registered: {}", persona);

        return Boolean.TRUE;
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

    /** Login a user. */
    public Persona login(@NonNull final String email, @NonNull final String password) {
        // Find Persona by email
        Persona persona = new QPersona().email.equalTo(email).findOne();
        if (persona == null) {
            throw new RuntimeException("User not found");
        }

        // Check if the password is correct
        if (!Password.check(password, persona.getPassword()).withBcrypt()) {
            throw new RuntimeException("Wrong password");
        }

        return persona;
    }

    /** Add a new Pic. */
    @Transactional
    public PicTwin addPic(@NonNull String ulidPersona, @NonNull Double latitude, @NonNull Double longitude, @NonNull File picture) {
        // Read the file
        byte[] data = FileUtils.readAllBytes(picture);

        // Find the Persona
        Persona persona = new QPersona().ulid.equalTo(ulidPersona).findOne();
        log.debug("Persona found: {}", persona);

        // Save the Pic
        Pic pic = Pic.builder()
                .latitude(latitude)
                .longitude(longitude)
                .reports(0)
                .date(Instant.now())
                .photo(data)
                .bloqued(false)
                .views(0)
                .persona(persona)
                .build();
        this.database.save(pic);
        log.debug("Pic saved: {}", pic);

        // Save the PicTwin
        PicTwin picTwin = PicTwin.builder()
                .expiration(Instant.now().plusSeconds(7 * 24 * 60 * 60)) // 1 week expiration
                .expired(false)
                .reported(false)
                .persona(persona)
                .pic(pic)
                .twin(pic) // FIXME: retrieve a new pic from the database
                .build();
        this.database.save(picTwin);

        return picTwin;
    }

    /** Get the PicTwins. */
    public List<PicTwin> getPicTwins(@NonNull String ulidPersona) {
        return new QPicTwin().persona.ulid.equalTo(ulidPersona).findList();
    }
}

