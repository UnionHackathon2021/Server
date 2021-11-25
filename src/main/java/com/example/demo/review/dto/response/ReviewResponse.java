package com.example.demo.review.dto.response;

import com.example.demo.review.Review;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReviewResponse {

    private Long id;

    private String content;

    private String sentiment;

    private float positive;

    private float negative;

    private float neutral;

    public static ReviewResponse of(Review review) {
        return ReviewResponse.builder()
                .id(review.getId())
                .content(review.getContent())
                .sentiment(review.getSentiment())
                .positive(review.getPositive())
                .negative(review.getNegative())
                .neutral(review.getNeutral())
                .build();
    }
}
