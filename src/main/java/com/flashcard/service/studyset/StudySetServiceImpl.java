package com.flashcard.service.studyset;

import com.flashcard.converter.StudySetConverter;
import com.flashcard.dto.StudySetDTO;
import com.flashcard.entity.StudySet;
import com.flashcard.repository.StudySetRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class StudySetServiceImpl implements StudySetService {
    private final Logger LOGGER = LogManager.getLogger(StudySetServiceImpl.class);
    @Autowired
    StudySetConverter studySetConverter;

    @Autowired
    StudySetRepository studySetRepository;

    @Override
    public void add(StudySetDTO studySetDTO) {
        StudySet studySet = studySetConverter.convertDTOToEntity(studySetDTO);
        studySet.setCreatedAt(LocalDateTime.now());
        studySet.setUpdatedAt(LocalDateTime.now());
        try {
            studySetRepository.save(studySet);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }
    }

    @Override
    public void update(StudySetDTO studySetDTO) {
        StudySet newStudySet = studySetConverter.convertDTOToEntity(studySetDTO);
        StudySet oldStudySet;
        try {
            Optional optionalStudySet = studySetRepository.findById(newStudySet.getStudySetId());
            if (optionalStudySet.isPresent()) {
                oldStudySet = (StudySet) optionalStudySet.get();
                newStudySet.setCreatedAt(oldStudySet.getCreatedAt());
                newStudySet.setUpdatedAt(LocalDateTime.now());
                newStudySet.setUser(oldStudySet.getUser());
                studySetRepository.save(newStudySet);
            }
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }
    }

    @Override
    public void delete(StudySetDTO studySetDTO) {
        StudySet studySet = studySetConverter.convertDTOToEntity(studySetDTO);
        try {
            studySetRepository.delete(studySet);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }
    }

    @Override
    public StudySetDTO findById(Long id) {
        StudySet studySet = null;
        try {
            Optional optionalStudySet = studySetRepository.findById(id);
            if (optionalStudySet.isPresent()) {
                studySet = (StudySet) optionalStudySet.get();
            }
            return studySetConverter.convertEntityToDTO(studySet);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }
        return null;
    }

    @Override
    public List<StudySetDTO> findByUserId(Long userId) {
        try {
            List<StudySet> studySets = studySetRepository.findByUser(userId);
            return studySetConverter.convertEntitiesToDTOs(studySets);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }
        return null;
    }
}
