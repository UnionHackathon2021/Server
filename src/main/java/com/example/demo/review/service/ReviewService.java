package com.example.demo.review.service;

import com.example.demo.review.Review;
import com.example.demo.review.dto.naver.ConfidenceDto;
import com.example.demo.review.dto.request.ReviewRequest;
import com.example.demo.review.dto.response.ReviewListResponse;
import com.example.demo.review.dto.response.ReviewResponse;
import com.example.demo.review.dto.naver.SentimentResponse;
import com.example.demo.review.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReviewService {

    @Value("${naver.url}")
    private String NAVER_URL;

    @Value("${naver.client-id}")
    private String NAVER_CLIENT_ID;

    @Value("${naver.secret}")
    private String NAVER_SECRET;

    private final ReviewRepository reviewRepository;

    public ReviewListResponse getReview() {
        List<Review> reviewList = reviewRepository.findAll();
        int size = reviewList.size();

        int negativeSize = reviewRepository.findBySentiment("negative").size();
        int positiveSize = reviewRepository.findBySentiment("positive").size();
        int neutralSize = reviewRepository.findBySentiment("neutral").size();

        return ReviewListResponse.builder()
                .reviewList(reviewList.stream().map(review -> ReviewResponse.builder()
                        .id(review.getId())
                        .content(review.getContent())
                        .sentiment(review.getSentiment())
                        .positive(toPercent(review.getPositive()))
                        .negative(toPercent(review.getNegative()))
                        .neutral(toPercent(review.getNeutral()))
                        .build()).collect(Collectors.toList()))
                .totalNegative(size != 0 ? toPercent((float) negativeSize/size) : 0)
                .totalNeutral(size != 0 ? toPercent((float) neutralSize/size) : 0)
                .totalPositive(size != 0 ? toPercent((float) positiveSize/size) : 0)
                .build();
    }

    public static float toPercent(float n) {
        return Float.parseFloat(String.format("%.2f", n));
    }

    public void createReview(ReviewRequest request) {
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.set("X-NCP-APIGW-API-KEY-ID", NAVER_CLIENT_ID);
        headers.set("X-NCP-APIGW-API-KEY", NAVER_SECRET);
        headers.set("Content-Type", "application/json");
        HttpEntity<ReviewRequest> requestHttpEntity = new HttpEntity<>(request, headers);   // 헤더 설정

        SentimentResponse sentimentResponse =
                restTemplate.postForObject(NAVER_URL, requestHttpEntity, SentimentResponse.class);  // 클로바 api에 post 요청

        assert sentimentResponse != null;

        ConfidenceDto confidence = sentimentResponse.getDocument().getConfidence();

        reviewRepository.save(Review.builder()
                .content(request.getContent())
                .sentiment(sentimentResponse.getDocument().getSentiment())
                .neutral(confidence.getNeutral())
                .negative(confidence.getNegative())
                .positive(confidence.getPositive())
                .build());
    }
}
