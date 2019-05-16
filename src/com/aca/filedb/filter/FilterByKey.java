package com.aca.filedb.filter;

public interface FilterByKey<Key> {
    boolean filter(Key key);
}
