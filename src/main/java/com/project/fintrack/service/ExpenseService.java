package com.project.fintrack.service;

import com.project.fintrack.dto.ExpenseDTO;
import com.project.fintrack.dto.IncomeDTO;
import com.project.fintrack.modal.CategoryModal;
import com.project.fintrack.modal.ExpenseModal;
import com.project.fintrack.modal.ProfileModal;
import com.project.fintrack.repository.CategoryRepository;
import com.project.fintrack.repository.ExpenseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ExpenseService {

    private final CategoryRepository categoryRepository;
    private final ExpenseRepository expenseRepository;
    private final ProfileService profileService;

    //Add a new expense
    public ExpenseDTO addExpense(ExpenseDTO dto) {
        ProfileModal profile = profileService.getCurrentProfile();
        CategoryModal category = categoryRepository.findById(dto.getCategoryId())
                .orElseThrow(() -> new RuntimeException("Category not found.."));
        ExpenseModal newExpense = toModal(dto, profile, category);
        newExpense = expenseRepository.save(newExpense);
        return toDTO(newExpense);
    }

    //Retrieve all expenses for current month/based on the start date and end date
    public List<ExpenseDTO> getCurrentMonthExpensesForCurrentUser() {
        ProfileModal profile = profileService.getCurrentProfile();
        LocalDate now = LocalDate.now();
        LocalDate startDate = now.withDayOfMonth(1);
        LocalDate endDate = now.withDayOfMonth(now.lengthOfMonth());
        List<ExpenseModal> list = expenseRepository.findByProfileIdAndDateBetween(profile.getId(), startDate, endDate);
        return list.stream().map(this::toDTO).toList();
    }

    //Delete expenses by Id
    public void deleteExpense(Long expenseId) {
        ProfileModal profile = profileService.getCurrentProfile();
        ExpenseModal modal = expenseRepository.findById(expenseId)
                .orElseThrow(() -> new RuntimeException("Expenses not found"));
        if (!modal.getProfile().getId().equals(profile.getId())) {
            throw new RuntimeException("unauthorized to delete this expense");
        }
        expenseRepository.delete(modal);

    }

    //Get latest 5 expenses
    public List<ExpenseDTO> getLatest5ExpensesForCurrentUser() {
        ProfileModal currentProfile = profileService.getCurrentProfile();
        List<ExpenseModal> list = expenseRepository.findTop5ByProfileIdOrderByDateDesc(currentProfile.getId());
        return list.stream().map(this::toDTO).toList();
    }

    //Get total expenses of current user
    public BigDecimal getTotalExpensesForCurrentUser() {
        ProfileModal currentProfile = profileService.getCurrentProfile();
        BigDecimal totalExpenseByProfileId = expenseRepository.findTotalExpenseByProfileId(currentProfile.getId());
        return totalExpenseByProfileId != null ? totalExpenseByProfileId : BigDecimal.ZERO;
    }

    //filter expenses
    public List<ExpenseDTO> filterExpenses(LocalDate startDate, LocalDate endDate, String keyword, Sort sort) {
        ProfileModal currentProfile = profileService.getCurrentProfile();
        List<ExpenseModal> list = expenseRepository.findByProfileIdAndDateBetweenAndNameContainingIgnoreCase(currentProfile.getId(),
                startDate, endDate, keyword, sort);
        return list.stream().map(this::toDTO).toList();
    }

    //Notifications
    public List<ExpenseDTO> getExpensesForUserOnDate(Long profileId, LocalDate date) {
        List<ExpenseModal> list = expenseRepository.findByProfileIdAndDate(profileId, date);
        return list.stream().map(this::toDTO).toList();
    }

    //helper methods
    private ExpenseModal toModal(ExpenseDTO dto, ProfileModal profile, CategoryModal category) {
        return ExpenseModal.builder()
                .name(dto.getName())
                .icon(dto.getIcon())
                .amount(dto.getAmount())
                .date(dto.getDate())
                .profile(profile)
                .category(category)
                .build();
    }

    private ExpenseDTO toDTO(ExpenseModal modal) {
        return ExpenseDTO.builder()
                .id(modal.getId())
                .name(modal.getName())
                .icon(modal.getIcon())
                .categoryId(modal.getCategory() != null ? modal.getCategory().getId() : null)
                .categoryName(modal.getCategory() != null ? modal.getCategory().getName() : "N/A")
                .amount(modal.getAmount())
                .date(modal.getDate())
                .createdAt(modal.getCreatedAt())
                .updatedAt(modal.getUpdatedAt())
                .build();
    }
}
