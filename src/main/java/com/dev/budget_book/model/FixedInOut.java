package com.dev.budget_book.model;

import lombok.Data;

import java.util.List;

@Data
public class FixedInOut {
    List<FixedIncome> fixedIncomes;
    List<FixedExpended> fixedExpendeds;
}
