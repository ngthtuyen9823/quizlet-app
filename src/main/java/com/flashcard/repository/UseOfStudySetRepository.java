package com.flashcard.repository;

import com.flashcard.entity.UseOfStudySet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UseOfStudySetRepository extends JpaRepository<UseOfStudySet, Long> {
    @Query(value = "SELECT * FROM UseOfStudySet WHERE StudySetId = ?1 AND UserId = ?2", nativeQuery = true)
    Optional<UseOfStudySet> findByStudySetIdAndUserId(Long StudySetId, Long UserId);
}
