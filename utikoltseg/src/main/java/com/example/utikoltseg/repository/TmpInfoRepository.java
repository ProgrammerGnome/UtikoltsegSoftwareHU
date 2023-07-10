package com.example.utikoltseg.repository;

import com.example.utikoltseg.model.TmpInfoModel;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TmpInfoRepository extends JpaRepository<TmpInfoModel, Long> {
    @Modifying
    @Transactional
    @Query(value = "TRUNCATE TABLE ideiglenes", nativeQuery = true)
    void truncateTable();
}
