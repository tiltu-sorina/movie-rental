package ro.ubb.fantastic3.server.service;


import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ro.ubb.fantastic3.common.model.Movie;
import ro.ubb.fantastic3.common.model.Transaction;
import ro.ubb.fantastic3.common.model.validators.GenericValidator;
import ro.ubb.fantastic3.common.model.validators.exceptions.ValidatorException;
import ro.ubb.fantastic3.common.service.MovieService;
import ro.ubb.fantastic3.server.repository.MovieRepository;
import ro.ubb.fantastic3.server.repository.TransactionRepository;

import javax.transaction.Transactional;
import javax.validation.ConstraintViolationException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class MovieServiceImpl implements MovieService {
    private static final Logger log = LoggerFactory.getLogger(MovieServiceImpl.class);
    private final MovieRepository movieRepository;
    private final TransactionRepository transactionRepository;


    private final GenericValidator<Movie> movieValidator;

    @Override
    public Movie addMovie(Movie movie) {
        try {
            movieValidator.validate(movie);
        } catch (ConstraintViolationException e) {
            log.error("addMovie " + Arrays.toString(e.getStackTrace()));
            throw new ValidatorException(e.getMessage());
        }
        return movieRepository.save(movie);
    }

    @Override
    public Movie getMovie(Long id) {
        return this.movieRepository.getOne(id);
    }

    @Override
    public void deleteMovie(Long id) {
        this.movieRepository.deleteById(id);
    }

    @Override
    public List<Movie> getMovies() {
        return this.movieRepository.findAll();
    }

    @Override
    @Transactional
    public Movie updateMovie(Movie movie) {
        try {
            movieValidator.validate(movie);
        } catch (ConstraintViolationException e) {
            log.error("updateMovie " + Arrays.toString(e.getStackTrace()));
            throw new ValidatorException(e.getMessage());
        }
        var updatedMovie = this.movieRepository.getOne(movie.getId());
        updatedMovie.setTitle(movie.getTitle());
        updatedMovie.setDescription(movie.getDescription());
        updatedMovie.setReleaseYear(movie.getReleaseYear());
        updatedMovie.setPrice(movie.getPrice());
        return updatedMovie;
    }

    @Override
    public Movie getMostRentedMovie() {
        return this.movieRepository.findByOrderByRentalCounterDesc().get(0);
    }

    @Override
    public List<Movie> searchMovie(String query) {
        return this.movieRepository.findAll()
                .stream().filter(movie -> movie.toString().toLowerCase().contains(query.toLowerCase())).collect(Collectors.toList());
    }

    @Override
    public List<Movie> getRentedMovies() {
        List<Movie> returnedMovies = this.movieRepository.findAll();
        List<Transaction> rentals = new ArrayList<>(this.transactionRepository.findAll());
        List<Long> rentedMoviesId = rentals.stream().filter(rentalTransaction -> rentalTransaction.getReturnedDate() == null)
                .map(Transaction::getRentedMovieId).toList();
        return returnedMovies.stream().filter(movie -> rentedMoviesId.contains(movie.getId()))
                .collect(Collectors.toList());
    }

    @Override
    public List<Movie> getRentedMoviesWithInterval(LocalDate startDate, LocalDate endDate) {
        List<Movie> returnedMovies = this.movieRepository.findAll();
        List<Transaction> rentals = new ArrayList<>(this.transactionRepository.findAll());
        List<Long> rentedMoviesIdBetweenDates = rentals.stream().filter(rentalTransaction -> rentalTransaction.getReturnedDate() != null)
                .filter(rentalTransaction -> rentalTransaction.getRentedDate().isAfter(startDate)
                        && rentalTransaction.getRentedDate().isBefore(endDate))
                .map(Transaction::getRentedMovieId).toList();
        return returnedMovies.stream().filter(movie -> rentedMoviesIdBetweenDates.contains(movie.getId()))
                .toList();
    }

    @Override
    public List<Movie> getMoviesByName(String name) {
        return this.movieRepository.findByTitle(name);
    }
}

