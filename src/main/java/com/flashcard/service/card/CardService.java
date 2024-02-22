package com.flashcard.service.card;

import com.flashcard.dto.CardDTO;

import java.util.List;

public interface CardService {
    void add(CardDTO cardDTO);
    void update(CardDTO cardDTO);
    void delete(CardDTO cardDTO);
    CardDTO findById(Long id);
    List<CardDTO> findByStudySet(Long studySetId);
}
