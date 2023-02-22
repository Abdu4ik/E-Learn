package com.tafakkoor.englishlearningplatform.domains;

import com.tafakkoor.englishlearningplatform.domains.newStructure.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@Builder
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class UsersTestsHistory implements BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false)
    private Integer test_id;
    @Column(nullable = false)
    private Integer user_id;
    @Column(nullable = false)
    private Integer question_id;
    @Column(nullable = false)
    private boolean is_correct;
    @Column(nullable = false)
    private Integer score;
    @CreationTimestamp
    @Column(nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    @Builder.Default
    private LocalDateTime created_at = LocalDateTime.now();
}
