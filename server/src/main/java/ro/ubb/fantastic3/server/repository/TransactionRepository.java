package ro.ubb.fantastic3.server.repository;

import org.springframework.stereotype.Repository;
import ro.ubb.fantastic3.common.model.Transaction;

@Repository
public interface TransactionRepository extends GenericRepository<Transaction, Long>{

}
