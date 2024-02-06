package com.flashcard.entity;

import com.flashcard.key.UseOfStudySetKey;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class UseOfStudySet {
    @EmbeddedId
    private UseOfStudySetKey useOfStudySet;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @MapsId("userId")
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @MapsId("studySetId")
    @JoinColumn(name = "study_set_id", nullable = false)
    private StudySet studySet;

    @Column(name = "last_viewed_at")
    private LocalDateTime lastViewedAt;
}
