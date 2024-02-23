package com.flashcard.converter;

import com.flashcard.dto.StudySetDTO;
import com.flashcard.dto.UserDTO;
import com.flashcard.entity.StudySet;
import com.flashcard.entity.User;
import com.flashcard.enums.EVisibility;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

@SpringBootTest
public class StudySetConverterTest {
    @Autowired
    StudySetConverter studySetConverter;

    @Test
    public void ConvertStudySetEntityToDTO() throws Exception {
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
                .visibility(EVisibility.valueOf("EVERYONE"))
                .user(user)
                .build();

        // When
        StudySetDTO studySetDTO = studySetConverter.convertEntityToDTO(studySet);

        // Then
        Assertions.assertThat(studySet.getStudySetId()).isEqualTo(studySetDTO.getStudySetId());
        Assertions.assertThat(studySet.getTitle()).isEqualTo(studySetDTO.getTitle());
        Assertions.assertThat(studySet.getDescription()).isEqualTo(studySetDTO.getDescription());
        Assertions.assertThat(studySet.getSharedLink()).isEqualTo(studySetDTO.getSharedLink());
        Assertions.assertThat(studySet.getVisibility()).isEqualTo(studySetDTO.getVisibility());
        Assertions.assertThat(studySet.getUser().getUserId()).isEqualTo(studySetDTO.getUser().getUserId());
    }

    @Test
    public void ConvertNullStudySetEntityToDTO() throws Exception {
        // Given
        StudySet studySet = null;

        // When
        StudySetDTO studySetDTO = studySetConverter.convertEntityToDTO(studySet);

        // Then
        Assertions.assertThat(studySetDTO).isNull();
    }

    @Test
    public void ConvertStudySetDTOToEntity() throws Exception {
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
                .visibility(EVisibility.valueOf("EVERYONE"))
                .user(userDTO)
                .build();

        // When
        StudySet studySet = studySetConverter.convertDTOToEntity(studySetDTO);

        // Then
        Assertions.assertThat(studySet.getStudySetId()).isEqualTo(studySetDTO.getStudySetId());
        Assertions.assertThat(studySet.getTitle()).isEqualTo(studySetDTO.getTitle());
        Assertions.assertThat(studySet.getDescription()).isEqualTo(studySetDTO.getDescription());
        Assertions.assertThat(studySet.getSharedLink()).isEqualTo(studySetDTO.getSharedLink());
        Assertions.assertThat(studySet.getVisibility()).isEqualTo(studySetDTO.getVisibility());
        Assertions.assertThat(studySet.getUser().getUserId()).isEqualTo(studySetDTO.getUser().getUserId());
    }

    @Test
    public void ConvertNullStudySetDTOToEntity() throws Exception {
        // Given
        StudySetDTO studySetDTO = null;

        // When
        StudySet studySet = studySetConverter.convertDTOToEntity(studySetDTO);

        // Then
        Assertions.assertThat(studySet).isNull();
    }
}
