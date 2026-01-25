package com.project.fintrack.repository;

import com.project.fintrack.modal.IncomeModal;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public interface IncomeRepository extends JpaRepository<IncomeModal, Long> {

    //select * from tbl_incomes where profile_id =?1 order by date desc;
    List<IncomeModal> findByProfileIdOrderByDateDesc(Long ProfileId);

    //select * from tbl_incomes where profile_id=?1 order by date desc limit 5
    List<IncomeModal> findTop5ByProfileIdOrderByDateDesc(Long ProfileId);

    @Query("SELECT SUM(e.amount) FROM IncomeModal e WHERE e.profile.id=:profileId")
    BigDecimal findTotalIncomeByProfileId(@Param("profileId") Long profileId);


    //select * from tbl_incomes where profile_id = ?1 and date between  ?2 and ?3  and name like %?4%
    List<IncomeModal> findByProfileIdAndDateBetweenAndNameContainingIgnoreCase(
            Long profileId,
            LocalDate startDate,
            LocalDate endDate,
            String keyword,
            Sort sort
    );

    //select * from tbl_incomes where profile_id = ?1 and date between  ?2 and ?3
    List<IncomeModal> findByProfileIdAndDateBetween(Long profileId, LocalDate startDate, LocalDate endDate);
}
