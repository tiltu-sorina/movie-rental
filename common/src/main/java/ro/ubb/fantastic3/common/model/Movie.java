package ro.ubb.fantastic3.common.model;

import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.Entity;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@NoArgsConstructor
@ToString
@AllArgsConstructor
@SuperBuilder
@EqualsAndHashCode(callSuper=false)
@Entity
public class Movie extends BaseEntity<Long> implements Serializable {

    @NotNull(message = "Movie title can not be null!")
    private String title;
    @NotNull(message = "Description can not be null!")
    private String description;
    @Min(value = 1920, message = "Movie cannot be older than 1920!")
    private int releaseYear;
    @Min(value = 0, message = "Stock must be positive!")
    private int numberInStock;
    @NotNull(message = "Price cannot be null!")
    private float price;
    @Min(value = 0L, message = "The value must be positive!")
    private long rentalCounter;

    public Movie(Long aLong, String title, String description, int releaseYear, int numberInStock, float price, long rentalCounter) {
        super(aLong);
        this.title = title;
        this.description = description;
        this.releaseYear = releaseYear;
        this.numberInStock = numberInStock;
        this.price = price;
        this.rentalCounter = rentalCounter;
    }
}
