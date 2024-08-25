package com.example.demo.Service;

import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import com.example.demo.Model.Movie;
import com.example.demo.Model.Review;
import com.example.demo.Repository.MovieRepository;
import com.example.demo.Repository.ReviewRepository;

@Service
public class ReviewService {
	
	private MongoTemplate mongo;
	
	@Autowired
	public ReviewService(MongoTemplate m) {
		mongo=m;
	}
	
	public Review addReview(String imdbId, String body) {
        Review r = mongo.insert(new Review(body));
//		mongo.update(Movie.class).matching(Criteria.where("imdbId").is(imdbId)).apply(new Update().push("reviews", r)).first();
		
        Query query = new Query(Criteria.where("imdbId").is(imdbId));
        Update update = new Update().push("reviews", r);
        mongo.updateFirst(query, update, Movie.class);
		return r;
	}
    
    public void deleteReview(ObjectId reviewId, String imdbId) {
        Query reviewQuery = new Query(Criteria.where("_id").is(reviewId));
        mongo.remove(reviewQuery, Review.class);

        Query movieQuery = new Query(Criteria.where("imdbId").is(imdbId));
        Update update = new Update().pull("reviews", new Query(Criteria.where("_id").is(reviewId)));
        mongo.updateFirst(movieQuery, update, Movie.class);
    }

    public Review updateReview(ObjectId reviewId, String newBody) {
        Query query = new Query(Criteria.where("_id").is(reviewId));
        Update update = new Update().set("body", newBody);
        mongo.updateFirst(query, update, Review.class);
        return mongo.findOne(query, Review.class);
    }
    
	
	
//	private ReviewRepository reviewRepository;
//	private MovieRepository movieRepository;
//    
//	@Autowired
//	public ReviewService(ReviewRepository rr, MovieRepository mr) {
//		reviewRepository=rr;
//		movieRepository=mr;
//	}
//	
//	public Review addReview2(String imdbId, String body) {
//		Review newReview = new Review(body);
//		newReview = reviewRepository.save(newReview);
//		Optional<Movie> optionalMovie = movieRepository.findByImdbId(imdbId);
//		if (optionalMovie.isPresent()) {
//		    Movie movie = optionalMovie.get();
//		    movie.getReviews().add(newReview);
//		    movieRepository.save(movie);
//		}
//		return newReview;
//	}
//	
//    public void deleteReview2(ObjectId reviewId, String imdbId) {
//        Optional<Movie> optionalMovie = movieRepository.findByImdbId(imdbId);
//        if (optionalMovie.isPresent()) {
//            Movie movie = optionalMovie.get();
//            movie.getReviews().removeIf(review -> review.getId().equals(reviewId));
//            movieRepository.save(movie);
//        }
//        reviewRepository.deleteById(reviewId);
//    }
//    
//    public Review updateReview2(ObjectId reviewId, String newBody) {
//        Optional<Review> optionalReview = reviewRepository.findById(reviewId);
//        if (optionalReview.isPresent()) {
//            Review review = optionalReview.get();
//            review.setBody(newBody);
//            return reviewRepository.save(review);
//        }
//        return null;
//    }
    

}
