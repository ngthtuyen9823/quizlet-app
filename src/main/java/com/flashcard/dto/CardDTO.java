package com.flashcard.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CardDTO {
    @NotBlank(message = "CardId can not be blank")
    private long cardId;
    @NotBlank(message = "Term can not be blank")
    private String term;
    @NotBlank(message = "Description can not be blank")
    private String description;
    @NotBlank(message = "Image can not be blank")
    private String image;
    @NotBlank(message = "StudySet can not be blank")
    private StudySetDTO studySet;
}
