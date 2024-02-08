package com.flashcard.repository;

import com.flashcard.entity.User;
import com.flashcard.entity.StudySet;
import com.flashcard.enums.EVisibility;
import com.flashcard.repository.StudySetRepository;
import com.flashcard.repository.UserRepository;
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
        StudySet studySet = createStudySet();

        StudySet savedStudySet = studySetRepository.save(studySet);

        Assertions.assertThat(savedStudySet).isNotNull();
    }

    @Test
    @Order(2)
    public void getStudySetTest() throws Exception {
        StudySet savedStudySet = null;
        Optional<StudySet> optionalStudySet = studySetRepository.findById(1L);

        if (optionalStudySet.isPresent()) {
            savedStudySet = optionalStudySet.get();
        }

        Assertions.assertThat(savedStudySet).isNotNull();
    }

    @Test
    @Order(3)
    public void getListCardsTest() throws Exception {
        List<StudySet> savedStudySets = studySetRepository.findAll();

        Assertions.assertThat(savedStudySets.size()).isGreaterThan(0);
    }

    @Test
    @Order(4)
    @Rollback(value = false)
    public void updateStudySetTest() throws Exception {
        savedStudySet.setCreatedAt(LocalDateTime.parse("2019-12-15T15:14:21.629"));

        StudySet newStudySet = studySetRepository.save(savedStudySet);

        Assertions.assertThat(newStudySet.getCreatedAt()).isEqualTo(savedStudySet.getCreatedAt());
    }


    @Test
    @Order(5)
    @Rollback(value = false)
    public void deleteStudySetTest() throws Exception {
        long savedStudySetId = savedStudySet.getStudySetId();

        studySetRepository.delete(savedStudySet);

        Assertions.assertThat(studySetRepository.findById(savedStudySetId)).isNotPresent();
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

