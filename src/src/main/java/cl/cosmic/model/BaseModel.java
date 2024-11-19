package cl.cosmic.model;

import com.github.f4b6a3.ulid.UlidCreator; // For generating ULIDs
import io.ebean.annotation.Index;           // Ebean annotations
import io.ebean.annotation.SoftDelete;     // Ebean annotations
import io.ebean.annotation.WhenCreated;    // Ebean annotations
import io.ebean.annotation.WhenModified;   // Ebean annotations
import jakarta.persistence.Column;          // For JPA column mapping
import jakarta.persistence.Id;             // For JPA ID annotation
import jakarta.persistence.MappedSuperclass; // To mark this as a base class for JPA
import jakarta.persistence.Version;        // For JPA versioning
import lombok.Getter;                     // Lombok for getters
import lombok.Setter;                     // Lombok for setters
import lombok.ToString;                   // Lombok for toString
import java.time.Instant;                  // For time-related fields

/**
 * Base Model Class.
 */
@ToString
@MappedSuperclass
public abstract class BaseModel {

    /** The Id. */
    @Getter @Setter @Id
    private Long id;

    /** The public Id. */
    @Getter
    @Index(unique = true)  // Unique index for ULID
    @Column(length = 26)    // Set the length of the ULID string
    private final String ulid = UlidCreator.getUlid().toLowerCase();  // Generate the ULID

    /** The Version. */
    @Getter @Setter @Version
    private Long version;

    /** The creation date. */
    @Getter @Setter @WhenCreated
    private Instant createdAt;

    /** The modified date. */
    @Getter @Setter @WhenModified
    private Instant modifiedAt;

    /** Softdeleted. */
    @SoftDelete
    private Boolean deleted;  // Boolean for soft deletion
}