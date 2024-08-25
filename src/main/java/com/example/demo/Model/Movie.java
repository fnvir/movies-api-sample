package com.example.demo.Model;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;


@Document(collection = "movies")
public class Movie {
	
    @Id
    private ObjectId id;
    private String imdbId,title,releaseDate,trailerLink,poster;
    private List<String> genres,backdrops;
    @DocumentReference(collection = "reviews")
    private List<Review> reviews;
    
    public Movie() {}
    
	public Movie(ObjectId id, String imdbId, String title, String releaseDate, String trailerLink, String poster,
			List<String> genres, List<String> backdrops) {
		super();
		this.id = id;
		this.imdbId = imdbId;
		this.title = title;
		this.releaseDate = releaseDate;
		this.trailerLink = trailerLink;
		this.poster = poster;
		this.genres = genres;
		this.backdrops = backdrops;
	}

    public ObjectId getId() {
		return id;
	}

	public void setId(ObjectId id) {
		this.id = id;
	}

	public String getImdbId() {
		return imdbId;
	}

	public void setImdbId(String imdbId) {
		this.imdbId = imdbId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getReleaseDate() {
		return releaseDate;
	}

	public void setReleaseDate(String releaseDate) {
		this.releaseDate = releaseDate;
	}

	public String getTrailerLink() {
		return trailerLink;
	}

	public void setTrailerLink(String trailerLink) {
		this.trailerLink = trailerLink;
	}

	public String getPoster() {
		return poster;
	}

	public void setPoster(String poster) {
		this.poster = poster;
	}

	public List<String> getGenres() {
		return genres;
	}

	public void setGenres(List<String> genres) {
		this.genres = genres;
	}

	public List<String> getBackdrops() {
		return backdrops;
	}

	public void setBackdrops(List<String> backdrops) {
		this.backdrops = backdrops;
	}

	public List<Review> getReviews() {
		return reviews;
	}

	public void setReviews(List<Review> reviews) {
		this.reviews = reviews;
	}
}