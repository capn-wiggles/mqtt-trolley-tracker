package com.capnwiggles.springmqttdemo.domain.deserializer;

import com.capnwiggles.springmqttdemo.domain.Trolley;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.Instant;
import java.util.Date;

@Component
public class TrolleyDeserializer extends JsonDeserializer<Trolley> {

    @Override
    public Trolley deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {

        ObjectCodec codec = jsonParser.getCodec();
        JsonNode node = codec.readTree(jsonParser);

        Trolley trolley = new Trolley();

        final Integer board = node.get("board").asInt();
        trolley.setBoard(board);

        final double latitude = node.get("latitude").asDouble();
        trolley.setLatitude(latitude);

        final double longitude = node.get("longitude").asDouble();
        trolley.setLongitude(longitude);

        final Integer speed = node.get("speed").asInt();
        trolley.setSpeed(speed);

        final Integer direction = node.get("direction").asInt();
        trolley.setDirection(direction);

        final Integer rtuId = node.get("rtu_id").asInt();
        trolley.setRtuId(rtuId);

        final Integer route = node.get("route").asInt();
        trolley.setRoute(route);

        final String timestamp = node.get("timestamp").asText();
        Instant instant = Instant.parse(timestamp);
        Date dateTime = Date.from(instant);
        trolley.setTimestamp(dateTime);

        return trolley;
    }
}
