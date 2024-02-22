package com.flashcard.converter;

import com.flashcard.dto.UserDTO;
import com.flashcard.entity.User;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserConverter {
    @Autowired
    private ModelMapper mapper;

    public List<UserDTO> convertEntitiesToDTOs(List<User> users) {
        return users.stream().map(this::convertEntityToDTO).collect(Collectors.toList());
    }

    public UserDTO convertEntityToDTO(User user) {
        if (user == null) return null;
        return mapper.map(user, UserDTO.class);
    }

    public List<User> convertDTOsToEntities(List<UserDTO> userDTOs) {
        return userDTOs.stream().map(this::convertDTOToEntity).collect(Collectors.toList());
    }

    public User convertDTOToEntity(UserDTO userDTO) {
        if (userDTO == null) return null;
        return mapper.map(userDTO, User.class);
    }
}
