package com.flashcard.dto;

import lombok.AllArgsConstructor;
import lombok.*;
import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDTO {
    private long userId;
    private String userName;
    private String email;
    private List<StudySetDTO> studySets;
}
