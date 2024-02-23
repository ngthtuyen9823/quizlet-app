package com.flashcard.entity;

import com.flashcard.enums.EVisibility;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "study_set")
public class StudySet {
    @Id
    @Column(name = "study_set_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long studySetId;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "shared_link")
    private String sharedLink;

    @Column(name = "visibility")
    @Enumerated(EnumType.STRING)
    private EVisibility visibility;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "studySet")
    private List<Card> cards;
}
