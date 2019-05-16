package com.aca.filedb;

import com.aca.filedb.util.Stringable;
import java.io.Serializable;

public class LongValueRepository<Value extends Serializable> extends StringableValueRepository<Long, Value> {

    public LongValueRepository(String repositoryName) {
        super(repositoryName, new Stringable<Long>() {
            @Override
            public Long fromString(String s) {
                return Long.parseLong(s);
            }

            @Override
            public String toString(Long aLong) {
                return aLong.toString();
            }
        });
    }

}
