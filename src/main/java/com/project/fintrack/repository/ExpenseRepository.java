package com.project.fintrack.repository;

import com.project.fintrack.modal.ExpenseModal;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public interface ExpenseRepository extends JpaRepository<ExpenseModal, Long> {

    //select * from tbl_expenses where profile_id =?1 order by date desc;
    List<ExpenseModal> findByProfileIdOrderByDateDesc(Long ProfileId);

    //select * from tbl_expenses where profile_id=?1 order by date desc limit 5
    List<ExpenseModal> findTop5ByProfileIdOrderByDateDesc(Long ProfileId);

    @Query("SELECT SUM(e.amount) FROM ExpenseModal e WHERE e.profile.id=:profileId")
    BigDecimal findTotalExpenseByProfileId(@Param("profileId") Long profileId);


    //select * from tbl_expenses where profile_id = ?1 and date between  ?2 and ?3  and name like %?4%
    List<ExpenseModal> findByProfileIdAndDateBetweenAndNameContainingIgnoreCase(
            Long profileId,
            LocalDate startDate,
            LocalDate endDate,
            String keyword,
            Sort sort
    );

    //select * from tbl_expenses where profile_id = ?1 and date between  ?2 and ?3
    List<ExpenseModal> findByProfileIdAndDateBetween(Long profileId, LocalDate startDate, LocalDate endDate);

    //select * form tbl_expenses where profile_id= ?1 and date =?2
    List<ExpenseModal> findByProfileIdAndDate(Long profileId, LocalDate date);
}
