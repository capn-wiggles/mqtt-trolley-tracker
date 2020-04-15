package com.capnwiggles.springmqttdemo.domain.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class TimestampSerializer extends JsonSerializer<Date> {

    @Override
    public void serialize(Date date, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {

        DateFormat formatter = new SimpleDateFormat("yyy-MM-dd HH:mm:ss");

        String formattedDate = formatter.format(date);

        jsonGenerator.writeString(formattedDate);

    }
}
