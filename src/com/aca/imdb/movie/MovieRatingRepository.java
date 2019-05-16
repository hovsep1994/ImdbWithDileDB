package com.aca.imdb.movie;

import com.aca.filedb.StringableValueRepository;
import com.aca.filedb.filter.FilterByKey;
import com.aca.filedb.util.Stringable;
import java.io.IOException;
import java.util.List;

public class MovieRatingRepository {

    private static final String MOVIE_USER_SEPARATOR = "/*/";

    private static MovieRatingRepository movieRatingRepository;

    private StringableValueRepository<RatingId, Double> repository;

    MovieRatingRepository() {
        repository = new StringableValueRepository<>("Rating.txt", new Stringable<RatingId>() {

            @Override
            public RatingId fromString(String s) {
                String[] split = s.split(MOVIE_USER_SEPARATOR);
                return new RatingId(Long.parseLong(split[0]), Long.parseLong(split[1]));
            }

            @Override
            public String toString(RatingId ratingId) {
                return ratingId.movieId + MOVIE_USER_SEPARATOR + ratingId.userId;
            }
        });
    }

    public static MovieRatingRepository getInstance() {
        if (movieRatingRepository == null) {
            movieRatingRepository = new MovieRatingRepository();
        }
        return movieRatingRepository;
    }

    public void rate(Long movieId, Long userId, Double rating) {
        RatingId ratingId = new RatingId(movieId, userId);
        try {
            checkIsUnique(ratingId);
            repository.put(ratingId, rating);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public Double getRating(Long movieId) {
        List<Double> list;
        try {
            list = repository.get(new FilterByKey<RatingId>() {
                @Override
                public boolean filter(RatingId ratingId) {
                    return ratingId.movieId.equals(movieId);
                }
            });
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        double sum = 0;
        for (Double rating : list) {
            sum += rating;
        }
        return sum / list.size();
    }


    private void checkIsUnique(RatingId ratingId) throws IOException {
        if (repository.get(ratingId) != null) {
            throw new RuntimeException("Can't rate second time for same movie");
        }

    }

    static class RatingId {
        Long movieId;
        Long userId;

        RatingId(Long movieId, Long userId) {
            this.movieId = movieId;
            this.userId = userId;
        }
    }


}
