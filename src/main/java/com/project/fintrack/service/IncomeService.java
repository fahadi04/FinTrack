package com.project.fintrack.service;

import com.project.fintrack.dto.ExpenseDTO;
import com.project.fintrack.dto.IncomeDTO;
import com.project.fintrack.modal.CategoryModal;
import com.project.fintrack.modal.ExpenseModal;
import com.project.fintrack.modal.IncomeModal;
import com.project.fintrack.modal.ProfileModal;
import com.project.fintrack.repository.CategoryRepository;
import com.project.fintrack.repository.IncomeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class IncomeService {
    private final CategoryRepository categoryRepository;
    private final IncomeRepository incomeRepository;
    private final ProfileService profileService;

    //Add a new income
    public IncomeDTO addIncome(IncomeDTO dto) {
        ProfileModal profile = profileService.getCurrentProfile();
        CategoryModal category = categoryRepository.findById(Long.valueOf(dto.getCategoryId()))
                .orElseThrow(() -> new RuntimeException("Category not found.."));
        IncomeModal newIncome = toModal(dto, profile, category);
        newIncome = incomeRepository.save(newIncome);
        return toDTO(newIncome);
    }

    //Retrieve all income for current month/based on the start date and end date
    public List<IncomeDTO> getCurrentMonthIncomesForCurrentUser() {
        ProfileModal profile = profileService.getCurrentProfile();
        LocalDate now = LocalDate.now();
        LocalDate startDate = now.withDayOfMonth(1);
        LocalDate endDate = now.withDayOfMonth(now.lengthOfMonth());
        List<IncomeModal> list = incomeRepository.findByProfileIdAndDateBetween(profile.getId(), startDate, endDate);
        return list.stream().map(this::toDTO).toList();
    }

    //Delete income by id
    public void deleteIncome(Long incomeId) {
        ProfileModal profile = profileService.getCurrentProfile();
        IncomeModal modal = incomeRepository.findById(incomeId)
                .orElseThrow(() -> new RuntimeException("Income not found..."));
        if (!modal.getProfile().getId().equals(profile.getId())) {
            throw new RuntimeException("Unauthorized to delete this income");
        }
        incomeRepository.deleteById(incomeId);
    }

    //Get 5 latest incomes for the current user
    public List<IncomeDTO> getLatest5IncomeForCurrentUser() {
        ProfileModal currentProfile = profileService.getCurrentProfile();
        List<IncomeModal> list = incomeRepository.findTop5ByProfileIdOrderByDateDesc(currentProfile.getId());
        return list.stream().map(this::toDTO).toList();
    }

    //Get total incomes for the current user
    public BigDecimal getTotalIncomeForCurrentUser() {
        ProfileModal currentProfile = profileService.getCurrentProfile();
        BigDecimal totalIncomeByProfileId = incomeRepository.findTotalIncomeByProfileId(currentProfile.getId());
        return totalIncomeByProfileId != null ? totalIncomeByProfileId : BigDecimal.ZERO;
    }

    //filter incomes
    public List<IncomeDTO> filterIncomes(LocalDate startDate, LocalDate endDate, String keyword, Sort sort) {
        ProfileModal currentProfile = profileService.getCurrentProfile();
        List<IncomeModal> list = incomeRepository.findByProfileIdAndDateBetweenAndNameContainingIgnoreCase(currentProfile.getId(),
                startDate, endDate, keyword, sort);
        return list.stream().map(this::toDTO).toList();
    }

    //helper methods
    private IncomeModal toModal(IncomeDTO dto, ProfileModal profileModal, CategoryModal category) {
        return IncomeModal.builder()
                .name(dto.getName())
                .icon(dto.getIcon())
                .amount(dto.getAmount())
                .date(dto.getDate())
                .profile(profileModal)
                .category(category)
                .build();
    }

    private IncomeDTO toDTO(IncomeModal modal) {
        return IncomeDTO.builder()
                .id(modal.getId())
                .name(modal.getName())
                .icon(modal.getIcon())
                .categoryId(String.valueOf(modal.getCategory() != null ? modal.getCategory().getId() : null))
                .categoryName(modal.getCategory() != null ? modal.getCategory().getName() : "N/A")
                .amount(modal.getAmount())
                .date(modal.getDate())
                .createdAt(modal.getCreatedAt())
                .updatedAt(modal.getUpdatedAt())
                .build();
    }
}
