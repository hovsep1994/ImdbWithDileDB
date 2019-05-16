package com.aca.imdb.movie;

import com.aca.filedb.DateValueRepository;
import com.aca.filedb.Repository;
import com.aca.filedb.StringValueRepository;
import com.aca.filedb.filter.FilterByKey;
import com.aca.imdb.repository.AbstractRepository;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

public class MovieRepository extends AbstractRepository<Movie> {

    private static MovieRepository movieRepository;

    private Repository<String, Long> titleRepository;
    private Repository<LocalDate, Long> premiereRepository;

    private MovieRepository() {
        super(Movie.class);
        titleRepository = new StringValueRepository<>("movietitle.txt");
        premiereRepository = new DateValueRepository<>("moviepremiere.txt");
        try {

            titleRepository.open();
            premiereRepository.open();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static MovieRepository getInstance() {
        if (movieRepository == null) {
            movieRepository = new MovieRepository();
        }
        return movieRepository;
    }

    @Override
    public Movie create(Movie movie) {
        super.create(movie);
        try {
            premiereRepository.put(movie.getPremierDate(), movie.getId());
            titleRepository.put(movie.getTitle(), movie.getId());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return movie;
    }

    public Movie search(String title) {
        return getById(getMovieIdByTitle(title));
    }

    private Long getMovieIdByTitle(String title) {
        try {
            return titleRepository.get(new FilterByKey<String>() {
                @Override
                public boolean filter(String movieTitle) {
                    return movieTitle.startsWith(title);
                }
            }).get(0);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private List<Long> getMovieIdByDate(LocalDate from, LocalDate to) {
        try {
            return premiereRepository.get(new FilterByKey<LocalDate>() {
                @Override
                public boolean filter(LocalDate localDate) {
                    return localDate.isAfter(from) && localDate.isBefore(to);
                }
            });
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
