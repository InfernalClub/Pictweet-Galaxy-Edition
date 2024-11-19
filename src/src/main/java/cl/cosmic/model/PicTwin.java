package cl.cosmic.model;

import io.ebean.annotation.NotNull;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.*;

import java.time.Instant;

/** The PicTwin. */
@Getter
@ToString(callSuper = true)
@AllArgsConstructor
@Builder
@Entity
public class PicTwin extends BaseModel {  // Extending BaseModel for common attributes

    /** The expiration date. */
    @NotNull private Instant expiration;

    /** The expired status. */
    @Builder.Default @NotNull private Boolean expired = Boolean.FALSE;

    /** The reported status. */
    @Builder.Default @NotNull private Boolean reported = Boolean.FALSE;

    /** The Persona relationship. */
    @ManyToOne(optional = false)
    private Persona persona;

    /** The Pic relationship. */
    @ToString.Exclude
    @ManyToOne(optional = false)
    private Pic pic;

    /** The Twin Pic relationship. */
    @ToString.Exclude
    @ManyToOne(optional = false)
    private Pic twin;
}
