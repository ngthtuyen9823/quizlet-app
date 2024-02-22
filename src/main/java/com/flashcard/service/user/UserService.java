package com.flashcard.service.user;

import com.flashcard.dto.UserDTO;

public interface UserService {
    void addUser(UserDTO userDTO);
    void updateUser(UserDTO userDTO);
    UserDTO findById(Long id);
}
