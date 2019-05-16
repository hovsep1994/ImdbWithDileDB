package com.aca.imdb.repository;

import java.io.Serializable;

public abstract class Persistable implements Serializable {

    private long id;

    public Persistable(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    @Override
    public String toString() {
        return "id : " + id;
    }
}
