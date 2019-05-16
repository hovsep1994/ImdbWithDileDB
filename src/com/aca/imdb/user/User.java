package com.aca.imdb.user;

import com.aca.imdb.repository.Persistable;

public class User extends Persistable {

    private static long idCounter;

    private String username;
    private String password;

    public User(String username, String password) {
        super(idCounter++);
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public boolean isCorrect(String username, String password) {
        return this.username.equals(username) && this.password.equals(password);
    }

}
