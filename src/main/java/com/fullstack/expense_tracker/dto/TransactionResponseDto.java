package com.fullstack.expense_tracker.dto;

import lombok.Data;

import java.time.LocalDate;

@Data

public class TransactionResponseDto {
    private Long id;
    private String title;
    private Double amount;
    private String category;
    private LocalDate date;
}
