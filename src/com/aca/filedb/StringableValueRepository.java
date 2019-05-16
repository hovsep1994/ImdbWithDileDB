package com.aca.filedb;

import com.aca.filedb.filter.FilterByKey;
import com.aca.filedb.filter.FilterByValue;
import com.aca.filedb.util.ObjectSerializer;
import com.aca.filedb.util.Stringable;
import java.io.IOException;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

public class StringableValueRepository<Key, Value extends Serializable> implements Repository<Key, Value> {

    private StringStringRepository repository;
    private Stringable<Key> keyStringable;
    private ObjectSerializer<Value> valueObjectSerializer;

    public StringableValueRepository(String filePath, Stringable<Key> keyStringable) {
        repository = new StringStringRepository(filePath);
        this.keyStringable = keyStringable;
        this.valueObjectSerializer = new ObjectSerializer<>();
    }

    @Override
    public void open() throws IOException {
        repository.open();
    }

    @Override
    public void put(Key key, Value value) throws IOException {
        repository.put(keyStringable.toString(key), valueObjectSerializer.toString(value));
    }

    @Override
    public Value get(Key key) throws IOException {
        String keyString = keyStringable.toString(key);
        return valueObjectSerializer.fromString(repository.get(keyString));
    }

    @Override
    public List<Value> getAll() throws IOException {
        List<Value> list = new LinkedList<>();
        for (String s : repository.getAll()) {
            Value value = valueObjectSerializer.fromString(s);
            list.add(value);
        }
        return list;
    }

    @Override
    public List<Value> get(FilterByKey<Key> filter) throws IOException {
        List<String> stringList = repository.get(new FilterByKey<String>() {
            @Override
            public boolean filter(String s) {
                return filter.filter(keyStringable.fromString(s));
            }
        });
        List<Value> list = new LinkedList<>();
        for (String s : stringList) {
            Value value = valueObjectSerializer.fromString(s);
            list.add(value);
        }
        return list;
    }

    @Override
    public List<Value> get(FilterByValue<Value> filter) throws IOException {
        List<String> stringList = repository.get(new FilterByValue<String>() {
            @Override
            public boolean filter(String s) {
                return filter.filter(valueObjectSerializer.fromString(s));
            }
        });
        List<Value> list = new LinkedList<>();
        for (String s : stringList) {
            Value value = valueObjectSerializer.fromString(s);
            list.add(value);
        }
        return list;
    }

    @Override
    public void close() throws IOException {
        repository.close();
    }
}
