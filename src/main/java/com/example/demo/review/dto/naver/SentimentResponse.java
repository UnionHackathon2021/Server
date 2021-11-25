package com.example.demo.review.dto.naver;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class SentimentResponse {
    private DocumentDto document;

    private float positive;

    private float negative;

    private float neutral;
}
