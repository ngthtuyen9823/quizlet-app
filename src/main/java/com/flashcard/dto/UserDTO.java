package com.flashcard.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.*;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDTO {
    @NotBlank(message="UserId can not be blank")
    private long userId;
    @NotBlank(message="Username can not be blank")
    private String userName;
    @NotBlank(message="Email can not be blank")
    private String email;
}
