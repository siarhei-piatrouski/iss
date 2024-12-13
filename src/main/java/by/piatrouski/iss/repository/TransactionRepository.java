package by.piatrouski.iss.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import by.piatrouski.iss.model.Transaction;
import by.piatrouski.iss.model.TransactionStatus;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    @Modifying
    @Query("update Transaction t set t.status = ?2 where t.id = ?1")
    void updateTransactionStatus(Long transactionId, TransactionStatus status);
}
