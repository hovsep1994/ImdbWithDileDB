package com.aca.imdb.repository;

import com.aca.filedb.LongValueRepository;
import com.aca.filedb.Repository;
import java.io.IOException;
import java.util.List;

public abstract class AbstractRepository<Model extends Persistable> {

    protected Repository<Long, Model> repository;

    protected AbstractRepository(Class<Model> aClass) {
        repository = new LongValueRepository<>(aClass.getSimpleName());
        try {
            repository.open();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public Model getById(Long id) {
        try {
            return repository.get(id);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public Model create(Model model) {
        try {
            repository.put(model.getId(), model);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return model;
    }

    public List<Model> getAll() {
        try {
            return repository.getAll();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
