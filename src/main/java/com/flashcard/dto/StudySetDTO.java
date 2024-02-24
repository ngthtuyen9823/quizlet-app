package com.flashcard.dto;

import com.flashcard.enums.EVisibility;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StudySetDTO {
    @NotBlank(message = "StudySetId can not be blank")
    private long studySetId;
    @NotBlank(message = "Title can not be blank")
    private String title;
    @NotBlank(message = "Description can not be blank")
    private String description;
    @NotBlank(message = "SharedLink can not be blank")
    private String sharedLink;
    @NotBlank(message = "Visibility can not be blank")
    private EVisibility visibility;
    @NotBlank(message = "User can not be blank")
    private UserDTO user;
}
