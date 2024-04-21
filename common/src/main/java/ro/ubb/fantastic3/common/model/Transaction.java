package ro.ubb.fantastic3.common.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@SuperBuilder
@EqualsAndHashCode(callSuper = false)
@ToString
@Entity
public class Transaction extends BaseEntity<Long> {
    @NotNull(message = "Movie can not be null!")
    private Long rentedMovieId;
    @NotNull(message = "Client can not be null!")
    private Long clientId;
    @NotNull(message = "Date can not be null!")
    private LocalDate rentedDate;
    private LocalDate returnedDate;
}
