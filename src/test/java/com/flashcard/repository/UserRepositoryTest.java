package com.flashcard.repository;

import com.flashcard.entity.User;
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
public class UserRepositoryTest {
    @Autowired
    private UserRepository userRepository;
    private User savedUser;

    @BeforeEach
    private void setup() throws Exception {
        savedUser = saveUser();
    }

    @Test
    @Order(1)
    @Rollback(value = false)
    public void saveUserTest() throws Exception {
        // Given
        User user = createUser();
        // When
        User savedUser = userRepository.save(user);
        // Then
        Assertions.assertThat(savedUser).isNotNull();
    }

    @Test
    @Order(2)
    public void getUserByIdTest() throws Exception {
        // Given
        User savedUser = null;
        // When
        Optional<User> optionalUser = userRepository.findById(1L);
        if (optionalUser.isPresent()) {
            savedUser = optionalUser.get();
        }
        // Then
        Assertions.assertThat(savedUser).isNotNull();
    }

    @Test
    @Order(3)
    public void getListUsersTest() throws Exception {
        // When
        List<User> users = userRepository.findAll();
        // Then
        Assertions.assertThat(users.size()).isGreaterThan(0);
    }

    @Test
    @Order(4)
    @Rollback(value = false)
    public void updateUserTest() throws Exception {
        // Given
        savedUser.setUserName("username");
        // When
        User newUser = userRepository.save(savedUser);
        // Then
        Assertions.assertThat(newUser.getUserName()).isEqualTo(savedUser.getUserName());
    }

    @Test
    @Order(5)
    @Rollback(value = false)
    public void deleteUserTest() throws Exception {
        // Given
       long userId = savedUser.getUserId();
       // When
       userRepository.delete(savedUser);
       // Then
       Assertions.assertThat(userRepository.findById(userId)).isNotPresent();
    }

    private User saveUser() throws Exception {
        User user = createUser();
        return userRepository.save(user);
    }

    private User createUser() {
        return User.builder()
                .userName("test1")
                .password("hello")
                .email("cmc@gmail.com")
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();
    }
}
