package com.fullstack.expense_tracker.controller;

import com.fullstack.expense_tracker.dto.TransactionRequestDto;
import com.fullstack.expense_tracker.dto.TransactionResponseDto;
import com.fullstack.expense_tracker.entity.User;
import com.fullstack.expense_tracker.service.TransactionService;
import com.fullstack.expense_tracker.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/transactions")
public class TransactionController {
    private final TransactionService transactionService;
    private final UserService userService;

    @PostMapping("/add")
    public TransactionResponseDto addTransaction(
            @RequestParam String email,
            @Valid @RequestBody TransactionRequestDto requestDTO) {

        User user = userService.findByEmail(email);
        return transactionService.addTransaction(user, requestDTO);
    }

    @GetMapping("/user")
    public List<TransactionResponseDto> getTransactions(@RequestParam String email) {

        User user = userService.findByEmail(email);
        return transactionService.getTransactions(user);
    }

    // --- ADD THIS SECTION ---
    @DeleteMapping("/{id}")
    public void deleteTransaction(@PathVariable Long id) {
        // This calls the service to remove the record from MySQL
        transactionService.deleteTransaction(id);
    }

    @PutMapping("/{id}")
    public TransactionResponseDto updateTransaction(
            @PathVariable Long id,
            @Valid @RequestBody TransactionRequestDto requestDTO) {
        // This will find the existing transaction and update its fields
        return transactionService.updateTransaction(id, requestDTO);
    }
}
