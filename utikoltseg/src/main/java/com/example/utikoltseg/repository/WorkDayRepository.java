package com.example.utikoltseg.repository;

import com.example.utikoltseg.model.WorkDayModel;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkDayRepository extends JpaRepository<WorkDayModel,Long> {
    @Modifying
    @Transactional
    @Query(value = "TRUNCATE TABLE munkanap", nativeQuery = true)
    void truncateTable();
}