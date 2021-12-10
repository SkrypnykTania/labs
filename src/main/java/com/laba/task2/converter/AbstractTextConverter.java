package com.laba.task2.converter;


import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import com.fasterxml.jackson.dataformat.javaprop.JavaPropsMapper;
import com.fasterxml.jackson.dataformat.javaprop.JavaPropsSchema;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.lang.reflect.ParameterizedType;
import java.nio.charset.Charset;


public class AbstractTextConverter<T> implements Converter<T>{

    final Class<T> type;
    protected AbstractTextConverter() {
        type = (Class) ((ParameterizedType) this.getClass()
                .getGenericSuperclass()).getActualTypeArguments()[0];
    }

    @Override
    public T deserializeFrom(File source) {
        JavaPropsMapper mapper = new JavaPropsMapper();
        mapper.configure(JsonGenerator.Feature.IGNORE_UNKNOWN, true);
        JavaPropsSchema schema = JavaPropsSchema.emptySchema()
                .withKeyValueSeparator(": ");

        try {
            return mapper.reader(schema).readValue(source,type);
                    //.readValue(source,type);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public void serializeTo(T source,File file) {
        JavaPropsMapper mapper = new JavaPropsMapper();
        JavaPropsSchema schema = JavaPropsSchema.emptySchema()
                .withKeyValueSeparator(": ");
        try {
            OutputStreamWriter out = new OutputStreamWriter(new FileOutputStream(file), Charset.forName("cp1251"));

            mapper.writer(schema).writeValue(out, source);
//            mapper.writer(schema).writeValue(file, source);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


}
