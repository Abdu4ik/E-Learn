package com.tafakkoor.englishlearningplatform.domains;

import jakarta.persistence.*;
import lombok.*;

@Entity
@AllArgsConstructor
@Builder
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class User_Story {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false)
    private Integer user_id;
    @Column(nullable = false)
    private Integer story_id;
    @Column(nullable = false, columnDefinition = "boolean default false")
    private boolean is_saved;
}
