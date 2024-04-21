package ro.ubb.fantastic3.client.service;

import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import ro.ubb.fantastic3.common.model.Movie;
import ro.ubb.fantastic3.common.service.MovieService;

import java.time.LocalDate;
import java.util.List;

@Primary
@Service
//@Qualifier("movieServiceClient")
@NoArgsConstructor
public class MovieServiceClient implements MovieService {
    @Autowired
    private MovieService movieService;

    @Override
    public Movie addMovie(Movie movie) {
        return this.movieService.addMovie(movie);
    }

    @Override
    public Movie getMovie(Long id) {
        return this.movieService.getMovie(id);
    }

    @Override
    public void deleteMovie(Long id) {
        this.movieService.deleteMovie(id);
    }

    @Override
    public List<Movie> getMovies() {
        return this.movieService.getMovies();
    }

    @Override
    public Movie updateMovie(Movie movie) {
        return this.movieService.updateMovie(movie);
    }

    @Override
    public Movie getMostRentedMovie() {
        return this.movieService.getMostRentedMovie();
    }

    @Override
    public List<Movie> searchMovie(String query) {
        return this.movieService.searchMovie(query);
    }

    @Override
    public List<Movie> getRentedMovies() {
        return this.movieService.getRentedMovies();
    }

    @Override
    public List<Movie> getRentedMoviesWithInterval(LocalDate startDate, LocalDate endDate) {
        return this.movieService.getRentedMoviesWithInterval(startDate, endDate);
    }

    @Override
    public List<Movie> getMoviesByName(String name) {
        return this.movieService.getMoviesByName(name);
    }
}
