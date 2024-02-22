package com.flashcard.converter;

import com.flashcard.dto.StudySetDTO;
import com.flashcard.entity.StudySet;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class StudySetConverter {
    @Autowired
    private ModelMapper mapper;

    public List<StudySetDTO> convertEntitiesToDTOs(List<StudySet> studySets) {
        return studySets.stream().map(this::convertEntityToDTO).collect(Collectors.toList());
    }

    public StudySetDTO convertEntityToDTO(StudySet studySet) {
        if (studySet == null) return null;
        StudySetDTO studySetDTO = mapper.map(studySet, StudySetDTO.class);
        studySetDTO.setUser(studySet.getUser().getUserName());
        return studySetDTO;
    }

    public List<StudySet> convertDTOsToEntities(List<StudySetDTO> studySetDTOs) {
        return studySetDTOs.stream().map(this::convertDTOToEntity).collect(Collectors.toList());
    }

    public StudySet convertDTOToEntity(StudySetDTO studySetDTO) {
        if (studySetDTO == null) return null;
        return mapper.map(studySetDTO, StudySet.class);
    }
}
