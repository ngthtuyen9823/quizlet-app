package com.flashcard.converter;

import com.flashcard.dto.CardDTO;
import com.flashcard.dto.StudySetDTO;
import com.flashcard.dto.UserDTO;
import com.flashcard.entity.Card;
import com.flashcard.entity.StudySet;
import com.flashcard.entity.User;
import com.flashcard.enums.EVisibility;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

@SpringBootTest
public class CardConverterTest {
    @Autowired
    private CardConverter cardConverter;

    @Test
    public void ConvertCardEntityToDTO() throws Exception {
        // Given
        User user = User.builder()
                .userId(1L)
                .userName("test")
                .password("hello")
                .email("cmc@gmail.com")
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();

        StudySet studySet = StudySet.builder()
                .studySetId(1L)
                .title("Test Title")
                .description("Test description")
                .sharedLink("http://sharedlinktest")
                .visibility(EVisibility.valueOf("everyone"))
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .user(user)
                .build();

        Card card = Card.builder()
                .cardId(1L)
                .term("Term Test")
                .description("Description Test")
                .image("http://example.test.com")
                .lastViewedAt(LocalDateTime.now())
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .studySet(studySet)
                .build();

        // When
        CardDTO cardDTO = cardConverter.convertEntityToDTO(card);

        // Then
        Assertions.assertThat(card.getCardId()).isEqualTo(cardDTO.getCardId());
        Assertions.assertThat(card.getTerm()).isEqualTo(cardDTO.getTerm());
        Assertions.assertThat(card.getDescription()).isEqualTo(cardDTO.getDescription());
        Assertions.assertThat(card.getImage()).isEqualTo(cardDTO.getImage());
        Assertions.assertThat(card.getStudySet().getStudySetId()).isEqualTo(cardDTO.getStudySet().getStudySetId());
    }

    @Test
    public void ConvertNullCardEntityToDTO() throws Exception {
        // Given
        Card card = null;

        // When
        CardDTO cardDTO = cardConverter.convertEntityToDTO(card);

        // Then
        Assertions.assertThat(cardDTO).isNull();
    }

    @Test
    public void ConvertCardDTOToEntity() throws Exception {
        // Given
        UserDTO userDTO = UserDTO.builder()
                .userId(1L)
                .userName("test")
                .email("cmc@gmail.com")
                .build();

        StudySetDTO studySetDTO = StudySetDTO.builder()
                .studySetId(1L)
                .title("Test Title")
                .description("Test description")
                .sharedLink("http://sharedlinktest")
                .visibility(EVisibility.valueOf("everyone"))
                .user(userDTO)
                .build();

        CardDTO cardDTO = CardDTO.builder()
                .cardId(1L)
                .term("Test term")
                .description("Test description")
                .image("http://testimages")
                .studySet(studySetDTO)
                .build();

        // When
        Card card = cardConverter.convertDTOToEntity(cardDTO);

        // Then
        Assertions.assertThat(card.getCardId()).isEqualTo(cardDTO.getCardId());
        Assertions.assertThat(card.getTerm()).isEqualTo(cardDTO.getTerm());
        Assertions.assertThat(card.getDescription()).isEqualTo(cardDTO.getDescription());
        Assertions.assertThat(card.getImage()).isEqualTo(cardDTO.getImage());
        Assertions.assertThat(card.getStudySet().getStudySetId()).isEqualTo(cardDTO.getStudySet().getStudySetId());
    }

    @Test
    public void ConvertNullCardDTOToEntity() throws Exception {
        // Given
        CardDTO cardDTO = null;

        // When
        Card card = cardConverter.convertDTOToEntity(cardDTO);

        // Then
        Assertions.assertThat(card).isNull();
    }
}
