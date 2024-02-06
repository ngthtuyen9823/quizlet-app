package com.flashcard.repository;

import com.flashcard.entity.StudySet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudySetRepository extends JpaRepository<StudySet, Long> {
}
