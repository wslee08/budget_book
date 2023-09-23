package com.dev.budget_book.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

import java.sql.Date;
import java.sql.Timestamp;

@Entity(name = "fixed_expenses")
@Data
public class FixedExpended {
    @Id
    int fixedExpensesId;
    String userId;
    int amount;
    String category;
    String description;
    Date date;
    Timestamp created_at;
    Timestamp updated_at;
}
