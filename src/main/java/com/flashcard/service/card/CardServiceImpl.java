package com.flashcard.service.card;

import com.flashcard.converter.CardConverter;
import com.flashcard.dto.CardDTO;
import com.flashcard.entity.Card;
import com.flashcard.repository.CardRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class CardServiceImpl implements CardService {
    private final Logger LOGGER = LogManager.getLogger(CardServiceImpl.class);
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
        card.setUpdatedAt(LocalDateTime.now());
        try {
            cardRepository.save(card);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }
    }

    @Override
    public void update(CardDTO cardDTO) {
        Card newCard = cardConverter.convertDTOToEntity(cardDTO);
        Card oldCard;
        try {
            Optional optionalCard = cardRepository.findById(newCard.getCardId());
            if (optionalCard.isPresent()) {
                oldCard = (Card) optionalCard.get();
                newCard.setCreatedAt(oldCard.getCreatedAt());
                newCard.setUpdatedAt(LocalDateTime.now());
                newCard.setStudySet(oldCard.getStudySet());
                cardRepository.save(newCard);
            }
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }
    }

    @Override
    public void delete(CardDTO cardDTO) {
        Card card = cardConverter.convertDTOToEntity(cardDTO);
        try {
            studySetRepository.delete(card);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }
    }

    @Override
    public CardDTO findById(Long id) {
        Card card = null;
        try {
            Optional optionalCard = cardRepository.findById(id);
            if (optionalCard.isPresent()) {
                card = (Card) optionalCard.get();
                return cardConverter.convertEntityToDTO(card);
            }
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }
        return null;
    }

    @Override
    public List<CardDTO> findByStudySet(Long studySetId) {
        try {
            List<Card> cards = cardRepository.findByStudySet(studySetId);
            return cardConverter.convertEntitiesToDTOs(cards);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }
        return null;
    }
}
