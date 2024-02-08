package com.flashcard.repository;

import com.flashcard.entity.User;
import com.flashcard.repository.UserRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

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
        User user = createUser();

        User savedUser = userRepository.save(user);

        Assertions.assertThat(savedUser).isNotNull();
    }

    @Test
    @Order(2)
    public void getUserTest() throws Exception {
        User savedUser = null;
        Optional<User> optionalUser = userRepository.findById(1L);

        if (optionalUser.isPresent()) {
            savedUser = optionalUser.get();
        }

        Assertions.assertThat(savedUser).isNotNull();
    }

    @Test
    @Order(3)
    public void getListUsersTest() throws Exception {
        List<User> users = userRepository.findAll();

        Assertions.assertThat(users.size()).isGreaterThan(0);
    }

    @Test
    @Order(4)
    @Rollback(value = false)
    public void updateUserTest() throws Exception {
        savedUser.setUserName("username");

        User newUser = userRepository.save(savedUser);

        Assertions.assertThat(newUser.getUserName()).isEqualTo(savedUser.getUserName());
    }

    @Test
    @Order(5)
    @Rollback(value = false)
    public void deleteUserTest() throws Exception {
       long userId = savedUser.getUserId();

       userRepository.delete(savedUser);

       Assertions.assertThat(userRepository.findById(userId)).isNotPresent();
    }

    private User saveUser() throws Exception {
        User user = createUser();
        return userRepository.save(user);
    }

    private User createUser() {
        return User.builder()
                .userName("test")
                .password("hello")
                .email("cmc@gmail.com")
                .build();
    }
}
