package com.example.demo.Controller;

import java.util.Map;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Model.Review;
import com.example.demo.Service.ReviewService;


@RestController
@RequestMapping("/reviews")
public class ReviewController {
	
	private ReviewService reviews;
	
	@Autowired
	public ReviewController(ReviewService r) {
		reviews=r;
	}
	
	@PostMapping("/")
	public ResponseEntity<Review> createReview(@RequestBody Map<String, String> payload) {
		Review r=reviews.addReview(payload.get("imdbId"),payload.get("body"));
		return new ResponseEntity<Review>(r,HttpStatus.OK);
	}
	
    @PostMapping("/{imdbId}")
    public ResponseEntity<Review> addReview(@PathVariable String imdbId, @RequestBody Map<String, String> payload) {
        Review createdReview = reviews.addReview(imdbId, payload.get("body"));
        return ResponseEntity.ok(createdReview);
    }

    @DeleteMapping("/{reviewId}")
    public ResponseEntity<Void> deleteReview(@PathVariable ObjectId reviewId, @RequestParam String imdbId) {
    	reviews.deleteReview(reviewId, imdbId);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{reviewId}")
    public ResponseEntity<Review> updateReview(@PathVariable ObjectId reviewId, @RequestBody Map<String, String> payload) {
        Review updatedReview = reviews.updateReview(reviewId, payload.get("body"));
        if (updatedReview != null) {
            return ResponseEntity.ok(updatedReview);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
	
	
}
