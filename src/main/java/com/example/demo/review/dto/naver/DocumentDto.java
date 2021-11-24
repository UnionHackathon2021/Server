package com.example.demo.review.dto.naver;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class DocumentDto {
    private String sentiment;

    private ConfidenceDto confidence;
}
