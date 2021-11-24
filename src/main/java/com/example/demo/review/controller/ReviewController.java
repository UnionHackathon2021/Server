package com.example.demo.review.controller;

import com.example.demo.review.dto.ReviewRequest;
import com.example.demo.review.dto.ReviewResponse;
import com.example.demo.review.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/review")
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;

    @GetMapping("/{reviewId}")
    public ReviewResponse getReview(@PathVariable long reviewId) {
        return reviewService.getReview();
    }

    @PostMapping
    public void createReview(@RequestBody ReviewRequest request) {
        reviewService.createReview(request);
    }
}
