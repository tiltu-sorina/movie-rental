package ro.ubb.fantastic3.server.service;


import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.ubb.fantastic3.common.model.Client;
import ro.ubb.fantastic3.common.model.Movie;
import ro.ubb.fantastic3.common.model.Transaction;
import ro.ubb.fantastic3.common.model.validators.GenericValidator;
import ro.ubb.fantastic3.common.model.validators.exceptions.*;
import ro.ubb.fantastic3.common.service.TransactionService;
import ro.ubb.fantastic3.server.repository.ClientRepository;
import ro.ubb.fantastic3.server.repository.GenericRepository;
import ro.ubb.fantastic3.server.repository.MovieRepository;
import ro.ubb.fantastic3.server.repository.TransactionRepository;

import javax.validation.ConstraintViolationException;
import java.time.LocalDate;
import java.util.*;

@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    private static final Logger logger = LoggerFactory.getLogger(TransactionServiceImpl.class);
    @Autowired
    private TransactionRepository transactionRepository;
    @Autowired
    private MovieRepository movieRepository;
    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private final GenericValidator<Transaction> transactionValidator;


    @Override
    public Transaction rentMovie(Long movieId, Long clientId, LocalDate rentedDate) {
        logger.trace("rentMovie - method started");
        Transaction rentalTransaction = Transaction.builder()
                .rentedMovieId(movieId)
                .clientId(clientId)
                .rentedDate(rentedDate)
                .build();
        Movie movie = movieRepository.findById(movieId).orElse(new Movie());
        Client client = clientRepository.findById(clientId).orElse(new Client());
        if (movie.getId() != null) {
            if (movie.getNumberInStock() == 0) {
                logger.warn("rentMovie - method stopped -> movie not in stock");
                throw new NotEnoughStockException("The movie is not in stock.");
            }
            if (movie.getNumberInStock() > 0) {
                if (client.getId() != null) {
                    if (client.isHasRentals()) {
                        logger.warn("rentMovie - method stopped -> client already has rentals");
                        throw new RentedException("You already have rented a movie. You need to bring it back.");
                    } else {
                        movie.setRentalCounter(movie.getRentalCounter() + 1);
                        movie.setNumberInStock(movie.getNumberInStock() - 1);
                        client.setHasRentals(true);

                        movieRepository.save(movie);
                        clientRepository.save(client);

                        logger.trace("rentMovie - method succeded -> result:", rentalTransaction);
                        return transactionRepository.save(rentalTransaction);
                    }
                }
            }
        }
                throw new RuntimeException("Unexpected condition in rentMovie method.");
            }


            @Override
            public Transaction returnRentedMovie (Client client, LocalDate returnedDate){
                logger.trace("returnRentedMovie - method started");

                List<Transaction> transactions = this.getTransactions();
                Optional<Transaction> returnedTransaction = transactions.stream()
                        .filter(transaction -> transaction.getReturnedDate() == null)
                        .findFirst();
                Transaction rentalTransaction = returnedTransaction.orElseThrow();
                rentalTransaction.setReturnedDate(returnedDate);

                Movie movie = movieRepository.findById(rentalTransaction.getRentedMovieId())
                        .orElseThrow();
                movie.setNumberInStock(movie.getNumberInStock() + 1);
                client.setHasRentals(false);

                try {
                    movieRepository.save(movie);
                    clientRepository.save(client);
                    logger.trace("returnRentedMovie - method succeded -> movie returned:", movie);
                    return transactionRepository.save(rentalTransaction);
                } catch (Exception e) {
                    logger.warn("returnRentedMovie - method stopped -> incorrect data for movie return");
                    throw new ServiceException(e);
                }
            }


            @Override
            public List<Transaction> getTransactions () {
                logger.trace("getTransactions - method started");
                List<Transaction> transactions = transactionRepository.findAll();
                logger.trace("getTransactions - method succeded -> result:", transactions);
                return transactions;
            }
        }
