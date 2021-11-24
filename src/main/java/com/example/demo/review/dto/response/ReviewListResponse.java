package com.example.demo.review.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class ReviewListResponse {

    private float totalPositive;

    private float totalNegative;

    private float totalNeutral;

    private List<ReviewResponse> reviewList;
}
