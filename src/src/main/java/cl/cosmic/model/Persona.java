package cl.cosmic.model;

import cl.cosmic.model.BaseModel;
import io.ebean.annotation.Index;
import io.ebean.annotation.NotNull;
import jakarta.persistence.Column;  // Corrected 'Jakarta' to lowercase
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;  // Corrected 'ALLArgsConstructor' to 'AllArgsConstructor'
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.time.Instant;

/** The Persona. */
@Getter
@ToString(callSuper = true)
@AllArgsConstructor  // Fixed typo here
@Builder
@Entity
public class Persona extends BaseModel {

    /** The email. */
    @NotNull
    @Index(unique = true)  // Fixed 'Index' annotation formatting
    private String email;

    /** The password. */
    @NotNull
    @Column(length = 72)
    private String password;

    /** The number of strikes. */
    @NotNull
    private Integer strikes;

    /** The blocked status. */
    @NotNull
    private Boolean blocked;  // Fixed typo from 'bloqued' to 'blocked'

    /** The blocked date. */
    private Instant blockedAt;
}
