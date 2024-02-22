package com.flashcard.converter;

import com.flashcard.dto.CardDTO;
import com.flashcard.dto.StudySetDTO;
import com.flashcard.entity.Card;
import com.flashcard.entity.StudySet;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CardConverter {
    @Autowired
    private ModelMapper mapper;

    @Autowired
    private StudySetConverter studySetConverter;

    public List<CardDTO> convertEntitiesToDTOs(List<Card> cards) {
        return cards.stream().map(this::convertEntityToDTO).collect(Collectors.toList());
    }

    public CardDTO convertEntityToDTO(Card card) {
        if (card == null) return null;
        CardDTO cardDTO = mapper.map(card, CardDTO.class);
        StudySetDTO studySetDTO = studySetConverter.convertEntityToDTO(card.getStudySet());
        cardDTO.setStudySet(studySetDTO);
        return cardDTO;
    }

    public List<Card> convertDTOsToEntities(List<CardDTO> cardDTOs) {
        return cardDTOs.stream().map(this::convertDTOToEntity).collect(Collectors.toList());
    }

    public Card convertDTOToEntity(CardDTO cardDTO) {
        if (cardDTO == null) return null;
        Card card = mapper.map(cardDTO, Card.class);
        StudySet studySet = studySetConverter.convertDTOToEntity(cardDTO.getStudySet());
        card.setStudySet(studySet);
        return card;
    }
}
