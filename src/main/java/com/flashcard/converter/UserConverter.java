package com.flashcard.converter;

import com.flashcard.dto.UserDTO;
import com.flashcard.entity.User;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserConverter {
    @Autowired
    private ModelMapper modelMapper;
    private UserDTO getUserDTO(User user) {
        return modelMapper.map(user, UserDTO.class);
    }


}
