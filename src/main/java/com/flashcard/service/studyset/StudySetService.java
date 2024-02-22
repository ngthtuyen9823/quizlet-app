package com.flashcard.service.studyset;

import com.flashcard.dto.StudySetDTO;

import java.util.List;

public interface StudySetService {
    void add(StudySetDTO studySetDTO);
    void update(StudySetDTO studySetDTO);
    void delete(StudySetDTO studySetDTO);
    StudySetDTO findById(Long studySetId);
    List<StudySetDTO> findByUserId(Long userId);
}
