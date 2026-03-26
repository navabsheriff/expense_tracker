package com.fullstack.expense_tracker.repository;

import com.fullstack.expense_tracker.entity.Transaction;
import com.fullstack.expense_tracker.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    // Fetch all transactions for a specific user
    List<Transaction> findByUser(User user);
}