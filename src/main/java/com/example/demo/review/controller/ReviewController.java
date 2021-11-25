package com.example.demo.review.controller;

import com.example.demo.review.dto.request.ReviewRequest;
import com.example.demo.review.dto.response.ReviewListResponse;
import com.example.demo.review.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/review")
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;

    @GetMapping
    public ReviewListResponse getReview() {
        return reviewService.getReview();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createReview(@RequestBody ReviewRequest request) {
        reviewService.createReview(request);
    }
}
