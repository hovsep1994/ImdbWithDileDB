package com.aca.imdb.app;

public enum ApplicationMode {
    ADMIN,
    USER,
    QUIT;

    public static ApplicationMode get(int actionNumber) {
        return values()[actionNumber];
    }

    @Override
    public String toString() {
        return this.name() + " (" + this.ordinal() + ")";
    }
}
