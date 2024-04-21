package ro.ubb.fantastic3.common.service;

import ro.ubb.fantastic3.common.model.Movie;

import java.time.LocalDate;
import java.util.List;

public interface MovieService {
    Movie addMovie(Movie movie);
    Movie getMovie(Long id);
    void deleteMovie(Long id);
    List<Movie> getMovies();
    Movie updateMovie(Movie movie);
    Movie getMostRentedMovie();
    List<Movie> searchMovie(String query);
    List<Movie> getRentedMovies();
    List<Movie> getRentedMoviesWithInterval(LocalDate startDate, LocalDate endDate);
    List<Movie> getMoviesByName(String name);
}
