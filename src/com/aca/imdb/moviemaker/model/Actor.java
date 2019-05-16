package com.aca.imdb.moviemaker.model;

public class Actor extends MovieMaker {

    private static long idCounter;

    public Actor(String name) {
        super(idCounter++, name);
    }

}
