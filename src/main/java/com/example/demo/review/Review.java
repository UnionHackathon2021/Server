package com.example.demo.review;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity @Getter @Builder
@AllArgsConstructor @NoArgsConstructor
public class Review {

    @Id @GeneratedValue
    @Column(name = "review_id")
    private Long id;

    @Column(name = "review_content")
    private String content;

    @Column(name = "review_positive")
    private float positive;

    @Column(name = "review_negative")
    private float negative;

    @Column(name = "review_neutral")
    private float neutral;
}
