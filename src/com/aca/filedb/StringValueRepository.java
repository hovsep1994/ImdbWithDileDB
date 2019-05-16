package com.aca.filedb;

import com.aca.filedb.util.Stringable;
import java.io.Serializable;

public class StringValueRepository<Value extends Serializable> extends StringableValueRepository<String, Value> {

    public StringValueRepository(String repositoryName) {
        super(repositoryName, new Stringable<String>() {
            @Override
            public String fromString(String s) {
                return s;
            }

            @Override
            public String toString(String s) {
                return s;
            }
        });
    }

}
