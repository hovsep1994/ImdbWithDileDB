package com.aca.imdb.user;

import com.aca.filedb.filter.FilterByValue;
import com.aca.imdb.repository.AbstractRepository;
import java.io.IOException;
import java.util.List;

class UserRepository<T extends User> extends AbstractRepository<T> {

    protected UserRepository(Class<T> aClass) {
        super(aClass);
    }

    public User getUser(String username, String password) {
        try {
            List<T> list = repository.get(new FilterByValue<T>() {
                @Override
                public boolean filter(T t) {
                    return t.isCorrect(username, password);
                }
            });
            if (list.isEmpty()) {
                return null;
            }
            return list.get(0);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    protected boolean isUnique(String username) {
        List<T> list;
        try {
            list = repository.get(new FilterByValue<T>() {
                @Override
                public boolean filter(T t) {
                    return t.getUsername().equals(username);
                }
            });
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return list.size() == 0;
    }

}
