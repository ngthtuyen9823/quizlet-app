package com.flashcard.converter;

import com.flashcard.dto.CardDTO;
import com.flashcard.entity.Card;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CardConverter {
    @Autowired
    private ModelMapper mapper;

    public List<CardDTO> convertEntitiesToDTOs(List<Card> cards) {
        return cards.stream().map(this::convertEntityToDTO).collect(Collectors.toList());
    }

    public CardDTO convertEntityToDTO(Card card) {
        if (card == null) return null;
        CardDTO cardDTO = mapper.map(card, CardDTO.class);
        cardDTO.setStudySet(card.getStudySet().getTitle());
        return cardDTO;
    }

    public List<Card> convertDTOsToEntities(List<CardDTO> cardDTOs) {
        return cardDTOs.stream().map(this::convertDTOToEntity).collect(Collectors.toList());
    }

    public Card convertDTOToEntity(CardDTO cardDTO) {
        if (cardDTO == null) return null;
        return mapper.map(cardDTO, Card.class);
    }
}
