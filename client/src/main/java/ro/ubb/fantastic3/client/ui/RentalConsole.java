package ro.ubb.fantastic3.client.ui;


import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ro.ubb.fantastic3.common.model.Client;
import ro.ubb.fantastic3.common.model.validators.exceptions.NotEnoughStockException;
import ro.ubb.fantastic3.common.model.validators.exceptions.RentedException;
import ro.ubb.fantastic3.common.model.validators.exceptions.ServiceException;
import ro.ubb.fantastic3.common.service.ClientService;
import ro.ubb.fantastic3.common.service.MovieService;
import ro.ubb.fantastic3.common.service.TransactionService;

import java.time.LocalDate;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;

@Component("rentalConsole")
@RequiredArgsConstructor
public class RentalConsole {
    @Autowired
    private final TransactionService transactionService;
    @Autowired
    private final MovieService movieService;
    @Autowired
    private final ClientService clientService;

//    @Autowired
//    public RentalConsole(TransactionService transactionService, MovieService movieService, ClientService clientService) {
//        this.transactionService = transactionService;
//        this.movieService = movieService;
//        this.clientService = clientService;
//    }

    public void runRentalMenu() {
        printRentalMenu();
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String option = scanner.next();
            if (option.equals("x")) {
                return;
            }
            switch (option) {
                case "1" -> rentMovie();
                case "2" -> returnMovie();
                case "3" -> getTransactions();
                default -> System.out.println("Nonexistent option");
            }
            printRentalMenu();
        }
    }


    private void printRentalMenu() {
        System.out.println("""
                1 - Rent a movie
                2 - Return a movie
                ********************
                3 - Get all transactions
                --------------------
                x - Back
                """);
    }

    private void rentMovie() {
        Scanner scanner = new Scanner(System.in).useDelimiter("\n");
        this.clientService.getClients()
                .stream().sorted((client1, client2) -> client1.getId().compareTo(client2.getId()))
                .forEach(client -> System.out.printf("| ID = %d | Name = %s %s |\n", client.getId(), client.getLastName(), client.getFirstName()));
        System.out.println("Please enter your id:");
        Long clientId = scanner.nextLong();
        this.movieService.getMovies().stream().filter(movie -> movie.getNumberInStock() != 0)
                .sorted((movie1, movie2) -> movie1.getId().compareTo(movie2.getId()))
                .forEach(movie -> System.out.printf("| %d | %s - %.2f |\n", movie.getId(), movie.getTitle(), movie.getPrice()));
        System.out.println("Please enter the movie id you want to rent:");
        var movieId = scanner.nextLong();
        try {
            this.transactionService.rentMovie(movieId, clientId, LocalDate.now());
        } catch (RentedException | NotEnoughStockException | ServiceException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
    }

    private void returnMovie() {
        Scanner scanner = new Scanner(System.in).useDelimiter("\n");
        Set<Client> clients = this.clientService.getClients().stream().filter(Client::isHasRentals).collect(Collectors.toSet());
        if (clients.isEmpty()) {
            System.out.println("*********************************");
            System.out.println("We do not have any movies rented.");
            System.out.println("*********************************");
            return;
        }
        clients.stream().sorted((client1, client2) -> client1.getId().compareTo(client2.getId()))
                .forEach(client -> System.out.printf("| ID = %d | Name = %s %s |\n", client.getId(), client.getLastName(), client.getFirstName()));
        System.out.println("Please enter your ID.");
        Long clientId = scanner.nextLong();
        var client = this.clientService.getClient(clientId);
        try {
            this.transactionService.returnRentedMovie(client, LocalDate.now());
        } catch (ServiceException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
    }

    private void getTransactions() {
        this.transactionService.getTransactions().forEach(System.out::println);
    }
}