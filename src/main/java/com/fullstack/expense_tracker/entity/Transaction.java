package com.fullstack.expense_tracker.entity;
import com.fullstack.expense_tracker.entity.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "transactions")
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String type;
    private Double amount;
    private LocalDate date;
    private String category;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

}
