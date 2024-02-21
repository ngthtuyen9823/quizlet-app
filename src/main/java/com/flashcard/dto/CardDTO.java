package com.flashcard.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CardDTO {
    private long cardId;
    private String term;
    private String description;
    private String image;
    private String studySet;
}
