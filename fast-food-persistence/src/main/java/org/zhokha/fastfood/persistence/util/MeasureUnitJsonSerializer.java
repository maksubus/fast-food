package org.zhokha.fastfood.persistence.util;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;

/**
 * author: maks
 * date: 08.08.15
 */
public class MeasureUnitJsonSerializer extends JsonSerializer<MeasureUnit> {

    @Override
    public void serialize(MeasureUnit value,
                          JsonGenerator generator,
                          SerializerProvider provider) throws IOException, JsonProcessingException {

        generator.writeStartObject();
        generator.writeFieldName("name");
        generator.writeString(value.name());
        generator.writeFieldName("shortCode");
        generator.writeString(value.getShortCode());
        generator.writeEndObject();
    }
}
