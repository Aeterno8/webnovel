package com.github.aeterno8.webnovel.models;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "novels")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Novel extends BaseEntity {

    @Column(nullable = false)
    private String title;

    @Column(length = 1000)
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name =  "author_id")
    private Author author;
}