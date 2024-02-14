package com.flashcard.repository;

import com.flashcard.entity.Card;
import com.flashcard.entity.StudySet;
import com.flashcard.entity.User;
import com.flashcard.enums.EVisibility;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@DataJpaTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class CardRepositoryTest {
    @Autowired
    private CardRepository cardRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private StudySetRepository studySetRepository;
    private User savedUser;
    private StudySet savedStudySet;
    private Card savedCard;

    @BeforeEach
    private void setup() throws Exception {
        savedUser = saveUser();
        savedStudySet = saveStudySet();
        savedCard = saveCard();
    }

    @Test
    @Order(1)
    @Rollback(value = false)
    public void saveCardTest() throws Exception {
        // Given
        Card card = createCard();
        // When
        Card savedCard = cardRepository.save(card);
        // Then
        Assertions.assertThat(savedCard).isNotNull();
    }

    @Test
    @Order(2)
    public void getCardTest() throws Exception {
        // Given
        Card savedCard = null;
        // When
        Optional<Card> optionalCard = cardRepository.findById(1L);
        if (optionalCard.isPresent()) {
            savedCard = optionalCard.get();
        }
        // Then
        Assertions.assertThat(savedCard).isNotNull();
    }

    @Test
    @Order(3)
    public void getListCardsTest() throws Exception {
        // When
        List<Card> savedCards = cardRepository.findAll();
        // Then
        Assertions.assertThat(savedCards.size()).isGreaterThan(0);
    }

    @Test
    @Order(4)
    @Rollback(value = false)
    public void updateCardTest() throws Exception {
        // Given
        savedCard.setCreatedAt(LocalDateTime.parse("2019-12-15T15:14:21.629"));
        // When
        Card newCard = cardRepository.save(savedCard);
        // Then
        Assertions.assertThat(newCard.getCreatedAt()).isEqualTo(savedCard.getCreatedAt());
    }


    @Test
    @Order(5)
    @Rollback(value = false)
    public void deleteCardTest() throws Exception {
        // Given
        long savedCardId = savedCard.getCardId();
        // When
        cardRepository.delete(savedCard);
        // Then
        Assertions.assertThat(cardRepository.findById(savedCardId)).isNotPresent();
    }

    private User saveUser() throws Exception {
        User user = User.builder()
                .userName("test")
                .password("hello")
                .email("cmc@gmail.com")
                .build();
        return userRepository.save(user);
    }

    private StudySet saveStudySet() throws Exception {
        StudySet studySet = StudySet.builder()
                .title("Test Title")
                .description("Test description")
                .sharedLink("http://sharedlinktest")
                .visibility(EVisibility.valueOf("everyone"))
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .user(savedUser)
                .build();
        return studySetRepository.save(studySet);
    }

    private Card saveCard() throws Exception {
        Card card = createCard();
        return cardRepository.save(card);
    }

    private Card createCard() {
        return Card.builder()
                .term("Term Test")
                .description("Description Test")
                .image("http://example.test.com")
                .lastViewedAt(LocalDateTime.now())
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .studySet(savedStudySet)
                .build();
    }
}
