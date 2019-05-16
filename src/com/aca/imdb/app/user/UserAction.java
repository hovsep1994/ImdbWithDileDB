package com.aca.imdb.app.user;

public enum UserAction {
    SEARCH,
    RATE,
    QUITE;

    public static UserAction getUserAction(int actionNumber) {
        return values()[actionNumber];
    }

    @Override
    public String toString() {
        return this.name() + "(" + this.ordinal() + ")";
    }
}
