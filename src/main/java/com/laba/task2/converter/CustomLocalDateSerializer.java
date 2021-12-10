package com.laba.task2.converter;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class CustomLocalDateSerializer extends StdSerializer<LocalDate> {

    private static DateTimeFormatter formatter =
            DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public CustomLocalDateSerializer() {
        this(null);
    }

    public CustomLocalDateSerializer(Class<LocalDate> t) {
        super(t);
    }
    @Override
    public void serialize(LocalDate value, JsonGenerator gen, SerializerProvider provider) throws IOException {
        if (value==null){
            gen.writeString("");
        }else
        gen.writeString(formatter.format(value));

    }
}
