package com.fullstack.expense_tracker.service;

import com.fullstack.expense_tracker.dto.TransactionRequestDto;
import com.fullstack.expense_tracker.dto.TransactionResponseDto;
import com.fullstack.expense_tracker.entity.Transaction;
import com.fullstack.expense_tracker.entity.User;
import com.fullstack.expense_tracker.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TransactionServiceImp implements TransactionService{
    private final TransactionRepository transactionRepository;
    @Override
    public TransactionResponseDto addTransaction(User user, TransactionRequestDto requestDTO) {
        Transaction transaction = new Transaction();
        transaction.setTitle(requestDTO.getTitle());
        transaction.setAmount(requestDTO.getAmount());
        transaction.setCategory(requestDTO.getCategory());
        transaction.setDate(requestDTO.getDate());
        transaction.setUser(user);

        transaction = transactionRepository.save(transaction);

        TransactionResponseDto responseDTO = new TransactionResponseDto();
        responseDTO.setId(transaction.getId());
        responseDTO.setTitle(transaction.getTitle());
        responseDTO.setAmount(transaction.getAmount());
        responseDTO.setCategory(transaction.getCategory());
        responseDTO.setDate(transaction.getDate());

        return responseDTO;
    }

    @Override
    public List<TransactionResponseDto> getTransactions(User user) {
        List<Transaction> transactions = transactionRepository.findByUser(user);
        return transactions.stream().map(t -> {
            TransactionResponseDto dto = new TransactionResponseDto();
            dto.setId(t.getId());
            dto.setTitle(t.getTitle());
            dto.setAmount(t.getAmount());
            dto.setCategory(t.getCategory());
            dto.setDate(t.getDate());
            return dto;
        }).collect(Collectors.toList());

    }

    @Override
    public void deleteTransaction(Long id) {
        transactionRepository.deleteById(id);
    }
    @Override
    public TransactionResponseDto updateTransaction(Long id, TransactionRequestDto requestDTO) {
        // 1. Retrieve the existing transaction by ID
        // If not found, throw an exception (this prevents creating a new record by mistake)
        Transaction transaction = transactionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Transaction not found with id: " + id));

        // 2. Update the entity fields with new values from the request DTO
        transaction.setTitle(requestDTO.getTitle());
        transaction.setAmount(requestDTO.getAmount());
        transaction.setCategory(requestDTO.getCategory());
        transaction.setDate(requestDTO.getDate());
        // Note: We don't change the User usually, as the transaction still belongs to the same person

        // 3. Save the updated entity
        // JPA is smart: since 'transaction' has an ID, .save() performs an UPDATE instead of an INSERT
        Transaction updatedTransaction = transactionRepository.save(transaction);

        // 4. Convert the updated Entity back to a Response DTO
        TransactionResponseDto responseDTO = new TransactionResponseDto();
        responseDTO.setId(updatedTransaction.getId());
        responseDTO.setTitle(updatedTransaction.getTitle());
        responseDTO.setAmount(updatedTransaction.getAmount());
        responseDTO.setCategory(updatedTransaction.getCategory());
        responseDTO.setDate(updatedTransaction.getDate());

        return responseDTO;
    }
}
