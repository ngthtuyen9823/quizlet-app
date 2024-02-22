package com.flashcard.repository;

import com.flashcard.entity.User;
import com.flashcard.entity.StudySet;
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
public class StudySetRepositoryTest {
    @Autowired
    private StudySetRepository studySetRepository;
    @Autowired
    private UserRepository userRepository;
    private User savedUser;
    private StudySet savedStudySet;

    @BeforeEach
    private void setup() throws Exception {
        savedUser = saveUser();
        savedStudySet = saveStudySet();
    }

    @Test
    @Order(1)
    @Rollback(value = false)
    public void saveStudySetTest() throws Exception {
        // Given
        StudySet studySet = createStudySet();

        // When
        StudySet savedStudySet = studySetRepository.save(studySet);

        // Then
        Assertions.assertThat(savedStudySet).isNotNull();
    }

    @Test
    @Order(2)
    public void getStudySetByIdTest() throws Exception {
        // Given
        StudySet savedStudySet = null;

        // When
        Optional<StudySet> optionalStudySet = studySetRepository.findById(1L);
        if (optionalStudySet.isPresent()) {
            savedStudySet = optionalStudySet.get();
        }

        // Then
        Assertions.assertThat(savedStudySet).isNotNull();
    }

    @Test
    @Order(3)
    public void getStudySetsByUserIdTest() throws Exception {
        // When
        List<StudySet> studySet = studySetRepository.findByUser(savedUser.getUserId());

        // Then
        Assertions.assertThat(studySet).isNotNull();
    }


    @Test
    @Order(4)
    public void getStudySetsTest() throws Exception {
        // When
        List<StudySet> savedStudySets = studySetRepository.findAll();

        // Then
        Assertions.assertThat(savedStudySets.size()).isGreaterThan(0);
    }

    @Test
    @Order(5)
    @Rollback(value = false)
    public void updateStudySetTest() throws Exception {
        // Given
        savedStudySet.setCreatedAt(LocalDateTime.parse("2019-12-15T15:14:21.629"));

        // When
        StudySet newStudySet = studySetRepository.save(savedStudySet);

        // Then
        Assertions.assertThat(newStudySet.getCreatedAt()).isEqualTo(savedStudySet.getCreatedAt());
    }


    @Test
    @Order(6)
    @Rollback(value = false)
    public void deleteStudySetTest() throws Exception {
        // Given
        long savedStudySetId = savedStudySet.getStudySetId();

        // When
        studySetRepository.delete(savedStudySet);

        // Then
        Assertions.assertThat(studySetRepository.findById(savedStudySetId)).isNotPresent();
    }

    private User saveUser() throws Exception {
        User user = User.builder()
                .userName("test")
                .password("hello")
                .email("cmc@gmail.com")
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();
        return userRepository.save(user);
    }

    private StudySet saveStudySet() throws Exception {
        StudySet studySet = createStudySet();
        return studySetRepository.save(studySet);
    }

    private StudySet createStudySet() {
        return StudySet.builder()
                .title("Test Title")
                .description("Test description")
                .sharedLink("http://sharedlinktest")
                .visibility(EVisibility.valueOf("everyone"))
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .user(savedUser)
                .build();
    }
}

