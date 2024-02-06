package com.flashcard;

import com.flashcard.entity.User;
import com.flashcard.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
public class UserRepositoryTest {
    @Autowired
    private UserRepository userRepository;

    @Test
    private void saveUserTest() throws Exception {
        User user = User.builder()
                .userId(1)
                .userName("test")
                .password("hello")
                .email("cmc@gmail.com")
                .build();

        userRepository.save(user);
        Assertions.assertAll(userRepository.getUserId()).isGreaterThan(1);

    }
}
