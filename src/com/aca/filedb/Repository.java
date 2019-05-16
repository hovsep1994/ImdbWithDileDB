package com.aca.filedb;

import com.aca.filedb.filter.FilterByKey;
import com.aca.filedb.filter.FilterByValue;
import java.io.Closeable;
import java.io.IOException;
import java.util.List;

public interface Repository<Key, Value> extends Closeable {

    void open() throws IOException;

    void put(Key key, Value value) throws IOException;

    Value get(Key key) throws IOException;

    List<Value> getAll() throws IOException;

    List<Value> get(FilterByKey<Key> filter) throws IOException;

    List<Value> get(FilterByValue<Value> filter) throws IOException;

}
