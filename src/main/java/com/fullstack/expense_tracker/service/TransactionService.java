package com.fullstack.expense_tracker.service;

import com.fullstack.expense_tracker.dto.TransactionRequestDto;
import com.fullstack.expense_tracker.dto.TransactionResponseDto;
import com.fullstack.expense_tracker.dto.UserRequestDTO;
import com.fullstack.expense_tracker.dto.UserResponseDTO;
import com.fullstack.expense_tracker.entity.User;

import java.util.List;

public interface TransactionService {
    TransactionResponseDto addTransaction(User user, TransactionRequestDto requestDTO);

    List<TransactionResponseDto> getTransactions(User user);
    void deleteTransaction(Long id);
    TransactionResponseDto updateTransaction(Long id, TransactionRequestDto requestDTO);
}
