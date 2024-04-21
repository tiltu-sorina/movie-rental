package ro.ubb.fantastic3.client.service;

import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import ro.ubb.fantastic3.common.model.Client;
import ro.ubb.fantastic3.common.model.Movie;
import ro.ubb.fantastic3.common.model.Transaction;
import ro.ubb.fantastic3.common.service.TransactionService;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Service
@Primary
@NoArgsConstructor
public class TransactionServiceClient implements TransactionService {
    @Autowired
    private TransactionService transactionService;

    @Override
    public Transaction rentMovie(Long movieId, Long clientId, LocalDate rentedDate) {
        return this.transactionService.rentMovie(movieId, clientId, rentedDate);
    }

    @Override
    public Transaction returnRentedMovie(Client client, LocalDate returnedDate) {
        return this.transactionService.returnRentedMovie(client, returnedDate);
    }

    @Override
    public List<Transaction> getTransactions() {
        return this.transactionService.getTransactions();
    }
}
