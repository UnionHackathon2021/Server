package com.example.demo.review.dto.naver;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ConfidenceDto {
    private float neutral;
    private float positive;
    private float negative;
}
