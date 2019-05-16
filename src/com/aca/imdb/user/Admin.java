package com.aca.imdb.user;

import com.aca.imdb.movie.Movie;
import com.aca.imdb.movie.MovieRepository;
import com.aca.imdb.moviemaker.model.Actor;
import com.aca.imdb.moviemaker.model.Producer;
import com.aca.imdb.moviemaker.model.Writer;
import com.aca.imdb.moviemaker.repository.ActorRepository;
import com.aca.imdb.moviemaker.repository.ProducerRepository;
import com.aca.imdb.moviemaker.repository.WriterRepository;

public class Admin extends User {

    public Admin(String username, String password) {
        super(username, password);
    }

    public void createMovie(Movie movie) {
        MovieRepository.getInstance().create(movie);
    }

    public void createActor(Actor actor) {
        ActorRepository.getInstance().create(actor);
    }

    public void createWriter(Writer writer) {
        WriterRepository.getInstance().create(writer);
    }

    public void createProducer(Producer producer) {
        ProducerRepository.getInstance().create(producer);
    }
}
