package ro.ubb.fantastic3.common.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.Entity;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper = false)
@SuperBuilder
public class Client extends BaseEntity<Long> implements Serializable {
    @NotNull(message = "First name cannot be null")
    private String firstName;
    @NotNull(message = "Last name cannot be null")
    private String lastName;
    @NotNull(message = "Email cannot be null")
    private String email;
    @NotNull(message = "Phone number cannot be null")
    private String phoneNumber;
    @NotNull(message = "This field cannot be null")
    private boolean hasRentals;
    private int activeRentals;

    public Client(Long aLong, String firstName, String lastName, String email, String phoneNumber, boolean hasRentals, int activeRentals) {
        super(aLong);
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.hasRentals = hasRentals;
        this.activeRentals = activeRentals;
    }
}

