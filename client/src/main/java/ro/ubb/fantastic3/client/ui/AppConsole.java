package ro.ubb.fantastic3.client.ui;


import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
@RequiredArgsConstructor
public class AppConsole {
    @Autowired
    private final MovieConsole movieConsole;
    @Autowired
    private final ClientConsole clientConsole;

    @Autowired
    private RentalConsole rentalConsole;

    @Autowired
    public AppConsole(MovieConsole movieConsole, ClientConsole clientConsole, RentalConsole rentalConsole) {
        this.movieConsole = movieConsole;
        this.clientConsole = clientConsole;
        this.rentalConsole = rentalConsole;
    }


    public void runMenu() {
        printMenu();

        Scanner scanner = new Scanner(System.in);

        while (true) {
            String option = scanner.next();
            if (option.equals("x")) {
                break;
            }
            switch (option) {
                case "1" -> movieConsole.runMovieMenu();
                case "2" -> clientConsole.runClientMenu();
                case "3" -> rentalConsole.runRentalMenu();
                default -> System.out.println("Nonexistent option");
            }
            printMenu();
        }
    }


    private void printMenu() {
        System.out.println("""
                1 - MOVIE CRUD
                2 - CLIENT CRUD
                3 - RENT MOVIE
                --------------------
                x - Exit
                """);
    }


}


