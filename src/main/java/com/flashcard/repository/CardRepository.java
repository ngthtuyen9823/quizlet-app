package com.flashcard.repository;

import com.flashcard.entity.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CardRepository extends JpaRepository<Card, Long> {
    @Query(value = "SELECT * FROM card WHERE study_set_id = :studySetId", nativeQuery = true)
    List<Card> findByStudySet(Long studySetId);
}
