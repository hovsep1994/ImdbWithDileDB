package com.aca.imdb.moviemaker.model;

public class Writer extends MovieMaker {

    private static long idCounter;

    public Writer(String name) {
        super(idCounter++, name);
    }
}
