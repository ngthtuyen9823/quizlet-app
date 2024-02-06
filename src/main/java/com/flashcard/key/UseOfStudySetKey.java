package com.flashcard.key;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serializable;
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Embeddable
public class UseOfStudySetKey implements Serializable {
    @Column(name = "user_id")
    private Long userId;
    @Column(name = "study_set_id")
    private Long studySetId;
}
