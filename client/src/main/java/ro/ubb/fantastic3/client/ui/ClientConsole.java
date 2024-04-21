package ro.ubb.fantastic3.client.ui;


import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ro.ubb.fantastic3.common.model.Client;
import ro.ubb.fantastic3.common.model.validators.exceptions.ClientNotFoundException;
import ro.ubb.fantastic3.common.model.validators.exceptions.ServiceException;
import ro.ubb.fantastic3.common.model.validators.exceptions.ValidatorException;
import ro.ubb.fantastic3.common.service.ClientService;

import java.util.List;
import java.util.Scanner;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class ClientConsole {

    @Autowired
    private final ClientService clientService;


    public void runClientMenu() {
        printClientMenu();

        Scanner scanner = new Scanner(System.in);

        while (true) {
            String option = scanner.next();
            if (option.equals("x")) {
                return;
            }
            switch (option) {
                case "1" -> addClient();
                case "2" -> getClient();
                case "3" -> updateClient();
                case "4" -> deleteClient();
                case "5" -> printClients();
                default -> System.out.println("Nonexistent option");
            }
            printClientMenu();
        }
    }

    private void printClientMenu() {
        System.out.println("""
                1 - Add client
                2 - Get one client
                3 - Update client
                4 - Delete client
                5 - Print all clients
                --------------------
                x - Back
                """);
    }

    private void printClients() {
        System.out.println("All clients: \n");
        List<Client> clients = clientService.getClients();
        clients.forEach(System.out::println);

    }

    private void getClient() {
        Scanner scanner = new Scanner(System.in).useDelimiter("\n");

        System.out.println("id = ");
        Long id = scanner.nextLong();

        try {
            System.out.println(this.clientService.getClient(id));
        } catch (ClientNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    private void addClient() {
        Scanner scanner = new Scanner(System.in).useDelimiter("\n");


        System.out.println("First name = ");
        String firstName = scanner.next();


        System.out.println("Last Name = ");
        String lastName = scanner.next();

        System.out.println("Email = ");
        String email = scanner.next();

        System.out.println("Phone number = ");
        String phoneNumber = scanner.next();

        Client client = Client.builder()
                .firstName(firstName)
                .lastName(lastName)
                .email(email)
                .phoneNumber(phoneNumber)
                .hasRentals(false)
                .activeRentals(0)
                .build();

        try {
            clientService.addClient(client);
        } catch (ValidatorException e) {
            System.out.println(e.getMessage());
        }

    }

    private void updateClient() {
        Scanner scanner = new Scanner(System.in).useDelimiter("\n");

        System.out.println("!!!This is an admin function!!!");

        List<Client> clients = clientService.getClients();
        clients.forEach(client -> {
            System.out.print("|Id = " + client.getId() + "| ");
            System.out.println(client);
        });

        System.out.println();
        System.out.println("Which client do you want to update?");

        System.out.println("id = ");
        Long id = scanner.nextLong();

        System.out.println("First name = ");
        String firstName = scanner.next();


        System.out.println("Last Name = ");
        String lastName = scanner.next();

        System.out.println("Email = ");
        String email = scanner.next();

        System.out.println("Phone number = ");
        String phoneNumber = scanner.next();

        Client client = Client.builder()
                .firstName(firstName)
                .lastName(lastName)
                .email(email)
                .phoneNumber(phoneNumber)
                .build();
        client.setId(id);
        try {
            clientService.updateClient(client);
        } catch (ValidatorException | ClientNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    private void deleteClient() {

        Scanner scanner = new Scanner(System.in).useDelimiter("\n");

        System.out.println("!!!This is an admin function!!!");

        List<Client> clients = clientService.getClients();
        clients.forEach(client -> {
            System.out.print("|Id = " + client.getId() + "| ");
            System.out.println(client);
        });

        System.out.println();
        System.out.println("Which client do you want to delete?");

        System.out.println("id = ");
        Long id = scanner.nextLong();

        try {
            clientService.deleteClient(id);

        } catch (ClientNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }
}
