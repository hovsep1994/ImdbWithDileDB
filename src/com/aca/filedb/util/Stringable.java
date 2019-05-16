package com.aca.filedb.util;

public interface Stringable<T> {

    T fromString(String s);

    String toString(T t);

}
