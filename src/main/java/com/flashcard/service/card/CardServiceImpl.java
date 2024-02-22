package com.flashcard.service.card;

import com.flashcard.converter.CardConverter;
import com.flashcard.dto.CardDTO;
import com.flashcard.entity.Card;
import com.flashcard.repository.CardRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public class CardServiceImpl implements CardService {
    @Autowired
    CardConverter cardConverter;

    @Autowired
    CardRepository cardRepository;

    @Autowired
    CardRepository studySetRepository;

    @Override
    public void add(CardDTO cardDTO) {
        Card card = cardConverter.convertDTOToEntity(cardDTO);

        card.setCreatedAt(LocalDateTime.now());
        card.setLastViewedAt(LocalDateTime.now());
        card.setUpdatedAt(LocalDateTime.now());

        cardRepository.save(card);
    }

    @Override
    public void update(CardDTO cardDTO) {
        Card newCard = cardConverter.convertDTOToEntity(cardDTO);
        Card oldCard;

        Optional optionalCard = cardRepository.findById(newCard.getCardId());
        if (optionalCard.isPresent()) {
            oldCard = (Card) optionalCard.get();
            newCard.setCreatedAt(oldCard.getCreatedAt());
            newCard.setUpdatedAt(LocalDateTime.now());
            newCard.setStudySet(oldCard.getStudySet());

            cardRepository.save(oldCard);
        }
    }

    @Override
    public void delete(CardDTO cardDTO) {
        Card card = cardConverter.convertDTOToEntity(cardDTO);
        studySetRepository.delete(card);
    }

    @Override
    public CardDTO findById(Long id) {
        Card card = null;

        Optional optionalCard = cardRepository.findById(id);
        if (optionalCard.isPresent()) {
            card = (Card) optionalCard.get();
        }
        return cardConverter.convertEntityToDTO(card);
    }

    @Override
    public List<CardDTO> findByStudySet(Long studySetId) {
        List<Card> cards = cardRepository.findByStudySet(studySetId);
        return  cardConverter.convertEntitiesToDTOs(cards);
    }
}
