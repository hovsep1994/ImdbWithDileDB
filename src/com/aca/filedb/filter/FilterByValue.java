package com.aca.filedb.filter;

public interface FilterByValue<Value> {
    boolean filter(Value value);
}
