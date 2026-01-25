package com.project.fintrack.service;

import com.project.fintrack.dto.ExpenseDTO;
import com.project.fintrack.dto.IncomeDTO;
import com.project.fintrack.dto.RecentTransactionDTO;
import com.project.fintrack.modal.ProfileModal;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.stream.Stream.concat;

@Service
@RequiredArgsConstructor
public class DashboardService {
    private final IncomeService incomeService;
    private final ExpenseService expenseService;
    private final ProfileService profileService;

    public Map<String, Object> getDashboardData() {
        ProfileModal currentProfile = profileService.getCurrentProfile();
        Map<String, Object> returnValue = new LinkedHashMap<>();
        List<IncomeDTO> latestIncomes = incomeService.getLatest5IncomeForCurrentUser();
        List<ExpenseDTO> latestExpenses = expenseService.getLatest5ExpensesForCurrentUser();
        List<RecentTransactionDTO> recentTransactions = concat(latestIncomes.stream().map(income -> RecentTransactionDTO
                        .builder()
                        .id(income.getId())
                        .name(income.getName())
                        .profileId(currentProfile.getId())
                        .amount(income.getAmount())
                        .icon(income.getIcon())
                        .date(income.getDate())
                        .createdAt(income.getCreatedAt())
                        .updatedAt(income.getUpdatedAt())
                        .type("income")
                        .build()),
                latestExpenses.stream().map(expense -> RecentTransactionDTO.builder()
                        .id(expense.getId())
                        .name(expense.getName())
                        .profileId(currentProfile.getId())
                        .icon(expense.getIcon())
                        .date(expense.getDate())
                        .amount(expense.getAmount())
                        .createdAt(expense.getCreatedAt())
                        .updatedAt(expense.getUpdatedAt())
                        .type("expense")
                        .build()))
                .sorted((a, b) -> {
                    int cmp = b.getDate().compareTo(a.getDate());
                    if (cmp == 0 && a.getCreatedAt() != null && b.getCreatedAt() != null) {
                        return b.getCreatedAt().compareTo(a.getCreatedAt());
                    }
                    return cmp;
                }).toList();
        returnValue.put("totalBalance", incomeService.getTotalIncomeForCurrentUser()
                .subtract(expenseService.getTotalExpensesForCurrentUser()));
        returnValue.put("totalIncome", incomeService.getTotalIncomeForCurrentUser());
        returnValue.put("totalExpense", expenseService.getTotalExpensesForCurrentUser());
        returnValue.put("recent5Expense", latestExpenses);
        returnValue.put("recent5Income", latestIncomes);
        returnValue.put("recentTransaction", recentTransactions);
        return returnValue;
    }
}
