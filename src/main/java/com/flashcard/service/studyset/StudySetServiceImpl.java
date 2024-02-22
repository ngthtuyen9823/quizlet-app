package com.flashcard.service.studyset;

import com.flashcard.converter.StudySetConverter;
import com.flashcard.dto.StudySetDTO;
import com.flashcard.entity.StudySet;
import com.flashcard.repository.StudySetRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public class StudySetServiceImpl implements StudySetService {
    @Autowired
    StudySetConverter studySetConverter;

    @Autowired
    StudySetRepository studySetRepository;

    @Override
    public void add(StudySetDTO studySetDTO) {
        StudySet studySet = studySetConverter.convertDTOToEntity(studySetDTO);

        studySet.setCreatedAt(LocalDateTime.now());
        studySet.setUpdatedAt(LocalDateTime.now());

        studySetRepository.save(studySet);
    }

    @Override
    public void update(StudySetDTO studySetDTO) {
        StudySet newStudySet = studySetConverter.convertDTOToEntity(studySetDTO);
        StudySet oldStudySet;

        Optional optionalStudySet = studySetRepository.findById(newStudySet.getStudySetId());
        if (optionalStudySet.isPresent()) {
            oldStudySet = (StudySet) optionalStudySet.get();
            newStudySet.setCreatedAt(oldStudySet.getCreatedAt());
            newStudySet.setUpdatedAt(LocalDateTime.now());
            newStudySet.setUser(oldStudySet.getUser());

            studySetRepository.save(newStudySet);
        }
    }

    @Override
    public void delete(StudySetDTO studySetDTO) {
        StudySet studySet = studySetConverter.convertDTOToEntity(studySetDTO);
        studySetRepository.delete(studySet);
    }

    @Override
    public StudySetDTO findById(Long id) {
        StudySet studySet = null;

        Optional optionalStudySet = studySetRepository.findById(id);
        if (optionalStudySet.isPresent()) {
            studySet = (StudySet) optionalStudySet.get();
        }
        return studySetConverter.convertEntityToDTO(studySet);
    }

    @Override
    public List<StudySetDTO> findByUserId(Long userId) {
        List<StudySet> studySets = studySetRepository.findByUser(userId);
        return studySetConverter.convertEntitiesToDTOs(studySets);
    }
}
