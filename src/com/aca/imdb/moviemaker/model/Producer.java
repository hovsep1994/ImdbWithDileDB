package com.aca.imdb.moviemaker.model;

public class Producer extends MovieMaker {

    private static long idCounter;

    public Producer(String name) {
        super(idCounter++, name);
    }

}
