package com.flashcard.converter;

import com.flashcard.dto.UserDTO;
import com.flashcard.entity.User;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

@SpringBootTest
public class UserConverterTest {
    @Autowired
    UserConverter userConverter;

    @Test
    public void ConvertUserEntityToDTO() throws Exception {
        // Given
        User user = User.builder()
                .userId(1L)
                .userName("test")
                .password("hello")
                .email("cmc@gmail.com")
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();

        // When
        UserDTO userDTO = userConverter.convertEntityToDTO(user);

        // Then
        Assertions.assertThat(user.getUserId()).isEqualTo(userDTO.getUserId());
        Assertions.assertThat(user.getUserName()).isEqualTo(userDTO.getUserName());
        Assertions.assertThat(user.getEmail()).isEqualTo(userDTO.getEmail());
    }

    @Test
    public void ConvertNullUserEntityToDTO() throws Exception {
        // Given
        User user = null;

        // When
        UserDTO userDTO = userConverter.convertEntityToDTO(user);

        // Then
        Assertions.assertThat(userDTO).isNull();
    }

    @Test
    public void ConvertUserDTOToEntity() throws Exception {
        // Given
        UserDTO userDTO = UserDTO.builder()
                .userId(1L)
                .userName("test")
                .email("cmc@gmail.com")
                .build();

        // When
        User user = userConverter.convertDTOToEntity(userDTO);

        // Then
        Assertions.assertThat(user.getUserId()).isEqualTo(userDTO.getUserId());
        Assertions.assertThat(user.getUserName()).isEqualTo(userDTO.getUserName());
        Assertions.assertThat(user.getEmail()).isEqualTo(userDTO.getEmail());
    }

    @Test
    public void ConvertNullUserDTOToEntity() throws Exception {
        // Given
        UserDTO userDTO = null;

        // When
        User user = userConverter.convertDTOToEntity(userDTO);

        // Then
        Assertions.assertThat(user).isNull();
    }
}
