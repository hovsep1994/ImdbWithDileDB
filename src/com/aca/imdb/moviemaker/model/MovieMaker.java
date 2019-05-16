package com.aca.imdb.moviemaker.model;

import com.aca.imdb.repository.Persistable;

public abstract class MovieMaker extends Persistable {

    private String name;

    public MovieMaker(long id, String name) {
        super(id);
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return super.toString() + ", name : " + name;
    }
}
