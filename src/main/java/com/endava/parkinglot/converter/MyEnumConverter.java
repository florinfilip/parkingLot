package com.endava.parkinglot.converter;

import com.endava.parkinglot.parking.Type;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.fasterxml.jackson.databind.util.Converter;
import org.springframework.stereotype.Component;

public class MyEnumConverter implements Converter<String, Type> {

    @Override
    public JavaType getInputType(TypeFactory typeFactory) {
        return null;
    }

    @Override
    public JavaType getOutputType(TypeFactory typeFactory) {
        return null;
    }

    @Override
    public Type convert(String source) {
       return Type.valueOf(source.toUpperCase());
    }
}