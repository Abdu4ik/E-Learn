package com.tafakkoor.englishlearningplatform.domains;

import com.tafakkoor.englishlearningplatform.domains.newStructure.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.Hibernate;

import java.util.Objects;

@Entity
@Builder
@Table(name = "variants")
@AllArgsConstructor
@Getter
@Setter
@RequiredArgsConstructor
public class Variants implements BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String variant;
    private boolean isCorrect;
    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    private Questions questions;
}
