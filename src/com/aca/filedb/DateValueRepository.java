package com.aca.filedb;

import com.aca.filedb.util.Stringable;
import java.io.Serializable;
import java.time.LocalDate;

public class DateValueRepository<Value extends Serializable> extends StringableValueRepository<LocalDate, Value> {

    public DateValueRepository(String repositoryName) {
        super(repositoryName, new Stringable<LocalDate>() {
            @Override
            public LocalDate fromString(String s) {
                return LocalDate.parse(s);
            }

            @Override
            public String toString(LocalDate localDate) {
                return localDate.toString();
            }
        });
    }
}
