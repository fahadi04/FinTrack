package com.project.fintrack.service;

import com.project.fintrack.dto.CategoryDTO;
import com.project.fintrack.modal.CategoryModal;
import com.project.fintrack.modal.ProfileModal;
import com.project.fintrack.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final ProfileService profileService;
    private final CategoryRepository categoryRepository;

    //save category
    public CategoryDTO saveCategory(CategoryDTO categoryDTO) {
        ProfileModal profileModal = profileService.getCurrentProfile();

        if (categoryRepository.existsByNameAndProfileId(categoryDTO.getName(), profileModal.getId())) {
            throw new RuntimeException("Category with this already exists");
        }
        CategoryModal newCategory = toModal(categoryDTO, profileModal);
        categoryRepository.save(newCategory);
        return toDTO(newCategory);
    }

    //get categories for current user
    public List<CategoryDTO> getCategoryForCurrentUser() {
        ProfileModal profileModal = profileService.getCurrentProfile();
        List<CategoryModal> categories = categoryRepository.findByProfileId(profileModal.getId());
        return categories.stream().map(this::toDTO).toList();
    }

    //get categories by type for current user
    public List<CategoryDTO> getCategoriesByTypeForCurrentUser(String type) {
        ProfileModal profileModal = profileService.getCurrentProfile();
        List<CategoryModal> modal = categoryRepository.findByTypeAndProfileId(type, profileModal.getId());
        return modal.stream().map(this::toDTO).toList();
    }

    public CategoryDTO updateCategory(Long categoryId, CategoryDTO dto) {
        ProfileModal profile = profileService.getCurrentProfile();
        CategoryModal existingCategory = categoryRepository.findByIdAndProfileId(categoryId, profile.getId())
                .orElseThrow(() -> new RuntimeException("Category not found or not accessible"));
        existingCategory.setName(dto.getName());
        existingCategory.setIcon(dto.getIcon());
        existingCategory.setType(dto.getType());
        existingCategory = categoryRepository.save(existingCategory);
        return toDTO(existingCategory);
    }

    //helper methods
    private CategoryModal toModal(CategoryDTO categoryDTO, ProfileModal profileModal) {
        return CategoryModal.builder()
                .name(categoryDTO.getName())
                .icon(categoryDTO.getIcon())
                .type(categoryDTO.getType())
                .profile(profileModal)
                .build();
    }

    private CategoryDTO toDTO(CategoryModal categoryModal) {
        return CategoryDTO.builder()
                .id(categoryModal.getId())
                .profileId(categoryModal.getProfile() != null ? categoryModal.getProfile().getId() : null)
                .name(categoryModal.getName())
                .type(categoryModal.getType())
                .icon(categoryModal.getIcon())
                .createdAt(categoryModal.getCreatedAt())
                .updatedAt(categoryModal.getUpdatedAt())
                .build();
    }
}
