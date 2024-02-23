package com.flashcard.repository;

import com.flashcard.entity.StudySet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface StudySetRepository extends JpaRepository<StudySet, Long> {
    @Query(value = "SELECT * FROM study_set WHERE user_id = :userId", nativeQuery = true)
    List<StudySet> findByUser(Long userId);
}
