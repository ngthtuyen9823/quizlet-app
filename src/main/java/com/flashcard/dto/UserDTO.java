package com.flashcard.dto;

import lombok.AllArgsConstructor;
import lombok.*;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDTO {
    private long userId;
    private String userName;
    private String email;
}
