package ro.ubb.fantastic3.common.service;

import ro.ubb.fantastic3.common.model.Client;
import ro.ubb.fantastic3.common.model.Movie;
import ro.ubb.fantastic3.common.model.Transaction;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;
public interface TransactionService {
    Transaction rentMovie(Long movieId, Long clientId, LocalDate rentedDate);
    Transaction returnRentedMovie(Client client, LocalDate returnedDate);
    List<Transaction> getTransactions();
}
