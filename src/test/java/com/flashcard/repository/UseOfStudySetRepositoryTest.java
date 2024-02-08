package com.flashcard.repository;

import com.flashcard.entity.StudySet;
import com.flashcard.entity.UseOfStudySet;
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
public class UseOfStudySetRepositoryTest {
    @Autowired
    private UseOfStudySetRepository useOfStudySetRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private StudySetRepository studySetRepository;
    private User savedUser;
    private StudySet savedStudySet;
    private UseOfStudySet savedUseOfStudySet;

    @BeforeEach
    private void setup() throws Exception {
        savedUser = saveUser();
        savedStudySet = saveStudySet();
        savedUseOfStudySet = saveUseOfStudySet();
    }

    @Test
    @Order(1)
    @Rollback(value = false)
    public void saveUseOfStudySetTest() throws Exception {
        // Given
        UseOfStudySet useOfStudySet = createUseOfStudySet();
        // When
        UseOfStudySet savedUseOfStudySet = useOfStudySetRepository.save(useOfStudySet);
        // Then
        Assertions.assertThat(savedUseOfStudySet).isNotNull();
    }

    @Test
    @Order(2)
    public void getUseOfStudySetTest() throws Exception {
        // Given
        UseOfStudySet savedUseOfStudySet = null;
        // When
        Optional<UseOfStudySet> optionalUseOfStudySet = useOfStudySetRepository.findById(1L);
        if (optionalUseOfStudySet.isPresent()) {
            savedUseOfStudySet = optionalUseOfStudySet.get();
        }
        // Then
        Assertions.assertThat(savedUseOfStudySet).isNotNull();
    }

    @Test
    @Order(3)
    public void getListUseOfStudySetsTest() throws Exception {
        // When
        List<UseOfStudySet> savedUseOfStudySets = useOfStudySetRepository.findAll();
        // Then
        Assertions.assertThat(savedUseOfStudySets.size()).isGreaterThan(0);
    }

    @Test
    @Order(4)
    @Rollback(value = false)
    public void updateUseOfStudySetTest() throws Exception {
        // Given
        savedUseOfStudySet.setLastViewedAt(LocalDateTime.parse("2019-12-15T15:14:21.629"));
        // When
        UseOfStudySet newUseOfStudySet = useOfStudySetRepository.save(savedUseOfStudySet);
        // Then
        Assertions.assertThat(newUseOfStudySet.getLastViewedAt()).isEqualTo(savedUseOfStudySet.getLastViewedAt());
    }


    @Test
    @Order(5)
    @Rollback(value = false)
    public void deleteUseOfStudySetTest() throws Exception {
        // Given
        long savedStudySetId = savedUseOfStudySet.getStudySet().getStudySetId();
        long savedUserId = savedUseOfStudySet.getUser().getUserId();
        // When
        useOfStudySetRepository.delete(savedUseOfStudySet);
        // Then
        Assertions.assertThat(useOfStudySetRepository.findByStudySetIdAndUserId(savedStudySetId, savedUserId)).isNotPresent();
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

    private UseOfStudySet saveUseOfStudySet() throws Exception {
        UseOfStudySet useOfStudySet = createUseOfStudySet();
        return useOfStudySetRepository.save(useOfStudySet);
    }

    private UseOfStudySet createUseOfStudySet() {
        return UseOfStudySet.builder()
                .user(savedUser)
                .studySet(savedStudySet)
                .lastViewedAt(LocalDateTime.now())
                .build();
    }
}
