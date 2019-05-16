package com.aca.imdb.app.admin;

public enum AdminAction {

    MOVIE,
    WRITER,
    PRODUCER,
    ACTOR,
    QUITE;

    public static AdminAction get(int actionNumber) {
        return values()[actionNumber];
    }

    @Override
    public String toString() {
        return this.name() + " (" + this.ordinal() + ")";
    }
}
