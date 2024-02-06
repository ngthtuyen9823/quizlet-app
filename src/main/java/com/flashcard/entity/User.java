package com.flashcard.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "user")
public class User {
    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long userId;

    @Column(name = "user_name", nullable=false)
    private String userName;

    @Column(name = "password", nullable=false)
    private String password;

    @Column(name = "email", nullable=false)
    private String email;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<StudySet> studySets;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<UseOfStudySet> useOfStudySets;
}
