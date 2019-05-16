package com.aca.filedb;

import com.aca.filedb.util.Stringable;
import java.io.Serializable;
import java.time.LocalDate;

public class RepositoryFactory {

    @SuppressWarnings("unchecked")
    public <Key, Value extends Serializable> Repository<Key, Value> create(Class<Key> keyClass, Class<Value> valueClass) {
        if (keyClass.equals(Long.class)) {
            return (Repository<Key, Value>) new LongValueRepository<Value>(valueClass.getSimpleName());
        }
        if (keyClass.equals(LocalDate.class)) {
            return (Repository<Key, Value>) new DateValueRepository<Value>(valueClass.getSimpleName());
        }
        if (keyClass.equals(Integer.class)) {
            return (Repository<Key, Value>) new StringableValueRepository<Integer, Value>(valueClass.getSimpleName(), new Stringable<Integer>() {
                @Override
                public Integer fromString(String s) {
                    return Integer.parseInt(s);
                }

                @Override
                public String toString(Integer integer) {
                    return integer.toString();
                }
            });
        }
        if (keyClass.equals(String.class)) {
            return (Repository<Key, Value>) new StringValueRepository<Value>(valueClass.getSimpleName());
        }
        throw new RuntimeException("No such repository");
    }

}
