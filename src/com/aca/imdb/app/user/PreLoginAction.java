package com.aca.imdb.app.user;

public enum PreLoginAction {
    LOGIN,
    REGISTER;

    public static PreLoginAction getPreLoginAction(int actionNumber) {
        return values()[actionNumber];
    }

    @Override
    public String toString() {
        return this.name() + "(" + this.ordinal() + ")";
    }
}
