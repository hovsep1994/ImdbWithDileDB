package com.aca.imdb.moviemaker.repository;

import com.aca.imdb.moviemaker.model.Actor;

public class ActorRepository extends MovieMakerRepository<Actor> {

    private static final ActorRepository REPOSITORY = new ActorRepository();

    private ActorRepository() {
        super(Actor.class);
    }

    public static ActorRepository getInstance() {
        return REPOSITORY;
    }

    @Override
    public Actor create(String name) {
        return super.create(new Actor(name));
    }

}
