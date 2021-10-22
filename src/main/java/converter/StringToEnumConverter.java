package converter;

import com.endava.parkinglot.parking.Type;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.fasterxml.jackson.databind.util.Converter;

public class StringToEnumConverter implements Converter<String, Type> {
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