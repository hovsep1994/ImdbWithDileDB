package com.aca.imdb.moviemaker.repository;

import com.aca.imdb.moviemaker.model.Writer;

public class WriterRepository extends MovieMakerRepository<Writer> {

    private static final WriterRepository REPOSITORY = new WriterRepository();

    public static WriterRepository getInstance() {
        return REPOSITORY;
    }

    private WriterRepository(){
        super(Writer.class);
    }

    @Override
    public Writer create(String name) {
        return super.create(new Writer(name));
    }

}
