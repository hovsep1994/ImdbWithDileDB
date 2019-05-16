package com.aca.imdb.moviemaker.repository;

import com.aca.imdb.moviemaker.model.Producer;

public class ProducerRepository extends MovieMakerRepository<Producer> {

    private static final ProducerRepository REPOSITORY = new ProducerRepository();

    public static ProducerRepository getInstance() {
        return REPOSITORY;
    }

    private ProducerRepository() {
        super(Producer.class);
    }

    @Override
    public Producer create(String name) {
        return super.create(new Producer(name));
    }
}
