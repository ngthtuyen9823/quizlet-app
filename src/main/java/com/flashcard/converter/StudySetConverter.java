package com.flashcard.converter;

import com.flashcard.dto.StudySetDTO;
import com.flashcard.dto.UserDTO;
import com.flashcard.entity.StudySet;
import com.flashcard.entity.User;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class StudySetConverter {
    @Autowired
    private ModelMapper mapper;

    @Autowired
    private UserConverter userConverter;

    public List<StudySetDTO> convertEntitiesToDTOs(List<StudySet> studySets) {
        return studySets.stream().map(this::convertEntityToDTO).collect(Collectors.toList());
    }

    public StudySetDTO convertEntityToDTO(StudySet studySet) {
        if (studySet == null) return null;
        StudySetDTO studySetDTO = mapper.map(studySet, StudySetDTO.class);
        UserDTO userDTO = userConverter.convertEntityToDTO(studySet.getUser());
        studySetDTO.setUser(userDTO);
        return studySetDTO;
    }

    public List<StudySet> convertDTOsToEntities(List<StudySetDTO> studySetDTOs) {
        return studySetDTOs.stream().map(this::convertDTOToEntity).collect(Collectors.toList());
    }

    public StudySet convertDTOToEntity(StudySetDTO studySetDTO) {
        if (studySetDTO == null) return null;
        StudySet studySet = mapper.map(studySetDTO, StudySet.class);
        User user = userConverter.convertDTOToEntity(studySetDTO.getUser());
        studySet.setUser(user);
        return studySet;
    }
}
