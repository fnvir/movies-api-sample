package com.example.demo.Repository;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.demo.Model.Review;

public interface ReviewRepository extends MongoRepository<Review, ObjectId> {

}
