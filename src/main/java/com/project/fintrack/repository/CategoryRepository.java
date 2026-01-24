package com.project.fintrack.repository;

import com.project.fintrack.modal.CategoryModal;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.OptionalLong;

public interface CategoryRepository extends JpaRepository<CategoryModal, Long> {

    //select * from tbl_categories where profile_id = ?1
    List<CategoryModal> findByProfileId(Long profileId);


    //select * from tbl_categories where profile_id = ?1 and id = ?2
    Optional<CategoryModal> findByIdAndProfileId(Long id, Long profileId);

    // select * from tbl_categories where type = ?1 and profile_id = ?2
    List<CategoryModal> findByTypeAndProfileId(String type, Long profileId);

    Boolean existsByNameAndProfileId(String name, Long profileId);
}


