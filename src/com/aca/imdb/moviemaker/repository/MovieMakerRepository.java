package com.aca.imdb.moviemaker.repository;

import com.aca.imdb.moviemaker.model.MovieMaker;
import com.aca.imdb.repository.AbstractRepository;

public abstract class MovieMakerRepository<T extends MovieMaker> extends AbstractRepository<T> {

    protected MovieMakerRepository(Class<T> aClass) {
        super(aClass);
    }

    public abstract T create(String name);

}
