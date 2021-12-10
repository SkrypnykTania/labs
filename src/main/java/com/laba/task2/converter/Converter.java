package com.laba.task2.converter;

import java.io.File;

public interface Converter<T> {
    T deserializeFrom(File source);
    void serializeTo(T source,File file);
}
