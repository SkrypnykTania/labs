package com.laba.task2.converter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.ParameterizedType;

public class AbstractJsonConverter<T>  implements Converter<T>{
    final Class<T> type;
    protected AbstractJsonConverter() {
        type = (Class) ((ParameterizedType) this.getClass()
                .getGenericSuperclass()).getActualTypeArguments()[0];
    }
    @Override
    public T deserializeFrom(File source) {
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            return objectMapper.readValue(source,type);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public  void serializeTo(T source,File file) {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
        try {
            objectMapper.writeValue(file,source);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
