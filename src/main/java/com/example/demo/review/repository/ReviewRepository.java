package com.example.demo.review.repository;

import com.example.demo.review.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {

    List<Review> findBySentiment(String sentiment);
}
