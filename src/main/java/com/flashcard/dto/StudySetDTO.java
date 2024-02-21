package com.flashcard.dto;

import com.flashcard.enums.EVisibility;
import lombok.*;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StudySetDTO {
    private long studySetId;
    private String title;
    private String description;
    private String sharedLink;
    private EVisibility visibility;
    private List<CardDTO> cards;
}