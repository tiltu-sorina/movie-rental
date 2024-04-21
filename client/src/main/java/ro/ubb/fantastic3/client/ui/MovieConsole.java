package ro.ubb.fantastic3.client.ui;


import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ro.ubb.fantastic3.common.model.Movie;
import ro.ubb.fantastic3.common.model.validators.exceptions.ServiceException;
import ro.ubb.fantastic3.common.service.MovieService;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class MovieConsole {
    @Autowired
    private final MovieService movieService;


    public void runMovieMenu() {
        printMovieMenu();

        Scanner scanner = new Scanner(System.in);

        while (true) {
            String option = scanner.next();
            if (option.equals("x")) {
                return;
            }
            switch (option) {
                case "1" -> addMovie();
                case "2" -> getMovie();
                case "3" -> searchMovie();
                case "4" -> updateMovie();
                case "5" -> deleteMovie();
                case "6" -> printMovies();
                case "7" -> getMovieByName();
                case "8" -> printMostRentedMovie();
                case "9" -> printRentedMovies();
                case "10" -> printRentemMoviesWithInterval();
                default -> System.out.println("Nonexistent option");
            }
            printMovieMenu();
        }
    }


    private void printMovieMenu() {
        System.out.println("""
                1 - Add movie
                2 - Get one movie
                3 - Search movie
                4 - Update movie
                5 - Delete movie
                6 - Print all movies
                7 - Get movie by name
                --------------------
                Filter Options
                --------------------
                8 - Most rented movie
                9 - Rented movies
                10 - Rented movies between dates
                --------------------
                x - Back
                """);
    }

    private void printMostRentedMovie() {
        System.out.println(this.movieService.getMostRentedMovie());
    }

    private void searchMovie() {
        Scanner scanner = new Scanner(System.in).useDelimiter("\n");
        System.out.println("Enter search query");
        String query = scanner.next();
        System.out.println(this.movieService.searchMovie(query));
    }

    private void printRentedMovies() {
        this.movieService.getRentedMovies().forEach(System.out::println);


    }

    private void printRentemMoviesWithInterval() {
        Scanner scanner = new Scanner(System.in).useDelimiter("\n");
        System.out.println("Enter start date[YYYY-MM-DD]:");
        LocalDate startDate = LocalDate.parse(scanner.next());
        System.out.println("Enter end date[YYYY-MM-DD]:");
        LocalDate endDate = LocalDate.parse(scanner.next());
        this.movieService.getRentedMoviesWithInterval(startDate, endDate).forEach(System.out::println);
    }


    private void getMovie() {
        Scanner scanner = new Scanner(System.in).useDelimiter("\n");

        System.out.println("id = ");
        Long id = scanner.nextLong();
        try {
            System.out.println(this.movieService.getMovie(id));
        } catch (ServiceException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
    }

    private void printMovies() {
        System.out.println("All movies: \n");
        List<Movie> movies = movieService.getMovies();
        movies.forEach(System.out::println);
    }

    private void addMovie() {
        Scanner scanner = new Scanner(System.in).useDelimiter("\n");


//        System.out.println("id = ");
//        Long id = scanner.nextLong();

        System.out.println("title = ");
        String title = scanner.next();


        System.out.println("description = ");
        String description = scanner.next();


        System.out.println("release year = ");
        int releaseYear = scanner.nextInt();

        System.out.println("number in stock = ");
        int numberInStock = scanner.nextInt();

        System.out.println("price = ");
        float price = scanner.nextFloat();

        System.out.println("rental counter = ");
        int rentalCounter = scanner.nextInt();


        Movie movie = Movie.builder().title(title)
                .description(description)
                .releaseYear(releaseYear)
                .numberInStock(numberInStock)
                .price(price)
                .rentalCounter(rentalCounter)
                .build();
        try {
            movieService.addMovie(movie);
        } catch (ServiceException e) {
            System.out.println(e.getMessage());
        }
    }

    private void updateMovie() {
        Scanner scanner = new Scanner(System.in).useDelimiter("\n");

        System.out.println("!!!This is an admin function!!!");

        List<Movie> movies = movieService.getMovies();
        movies.forEach(movie -> {
            System.out.print("|Id = " + movie.getId() + "| ");
            System.out.println(movie);
        });
        System.out.println();
        System.out.println("Wich movie you want to update?");

        System.out.println("id = ");
        Long id = scanner.nextLong();

        System.out.println("title = ");
        String title = scanner.next();


        System.out.println("description = ");
        String description = scanner.next();


        System.out.println("release year = ");
        int releaseYear = scanner.nextInt();

        System.out.println("number in stock = ");
        int numberInStock = scanner.nextInt();

        System.out.println("price = ");
        float price = scanner.nextFloat();

        Movie movie = new Movie(title, description, releaseYear, numberInStock, price,0);
        movie.setId(id);
        try {
            this.movieService.updateMovie(movie);
        } catch (ServiceException e) {
            System.out.println(e.getMessage());
        }

    }

    private void deleteMovie() {
        Scanner scanner = new Scanner(System.in).useDelimiter("\n");

        System.out.println("!!!This is an admin function!!!");

        List<Movie> movies = movieService.getMovies();
        movies.forEach(movie -> {
            System.out.println("|Id = " + movie.getId() + "| " + movie.getTitle());

        });
        System.out.println();
        System.out.println("Wich movie you want to delete?");

        System.out.println("id = ");
        Long id = scanner.nextLong();
        try {
            this.movieService.deleteMovie(id);
        } catch (ServiceException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }

    }

    private void getMovieByName() {
        Scanner scanner = new Scanner(System.in).useDelimiter("\n");
        System.out.println("Enter movie name");
        String name = scanner.next();
        this.movieService.getMoviesByName(name).forEach(System.out::println);
    }


}
