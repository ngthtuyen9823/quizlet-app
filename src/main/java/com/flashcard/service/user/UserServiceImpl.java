package com.flashcard.service.user;

import com.flashcard.converter.UserConverter;
import com.flashcard.dto.UserDTO;
import com.flashcard.entity.User;
import com.flashcard.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserConverter userConverter;
    @Autowired
    private UserRepository userRepository;

    @Override
    public void addUser(UserDTO userDTO) {
        User user = userConverter.convertDTOToEntity(userDTO);

        user.setCreatedAt(LocalDateTime.now());
        user.setUpdatedAt(LocalDateTime.now());

        userRepository.save(user);
    }

    @Override
    public void updateUser(UserDTO userDTO) {
        User newUser = userConverter.convertDTOToEntity(userDTO);
        User oldUser;

        Optional optionalUser = userRepository.findById(newUser.getUserId());
        if (optionalUser.isPresent()) {
            oldUser = (User) optionalUser.get();
            newUser.setUpdatedAt(LocalDateTime.now());
            newUser.setPassword(oldUser.getPassword());
            newUser.setStudySets(oldUser.getStudySets());
            userRepository.save(newUser);
        }
    }

    @Override
    public UserDTO findById(Long id) {
        User user = new User();
        Optional optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()) {
            user = (User) optionalUser.get();
        }
        return userConverter.convertEntityToDTO(user);
    }
}
