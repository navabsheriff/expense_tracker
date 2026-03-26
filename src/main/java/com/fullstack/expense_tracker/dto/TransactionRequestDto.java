package com.fullstack.expense_tracker.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

import java.time.LocalDate;

@Data
public class TransactionRequestDto {
    @NotBlank(message = "Title cannot be empty")
    private String title;

    @NotNull(message = "Amount required")
    @Positive(message = "Amount must be positive")
    private Double amount;

    @NotBlank(message = "Category required")
    private String category;

    @NotNull(message = "Date required")
    private LocalDate date;
}
