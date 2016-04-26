package org.zhokha.fastfood.persistence.util;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;

import java.io.IOException;

/**
 * author: maks
 * date: 08.08.15
 */
public class MeasureUnitJsonDeserializer extends JsonDeserializer<MeasureUnit> {

    @Override
    public MeasureUnit deserialize(JsonParser jsonParser,
                                   DeserializationContext deserializationContext)
            throws IOException, JsonProcessingException {
        jsonParser.getValueAsString();

        JsonNode node = jsonParser.readValueAsTree();

        if ("STRING".equals(node.getNodeType().name())) {
            return MeasureUnit.valueOf(node.textValue());
        } else if ("OBJECT".equals(node.getNodeType().name())){
            return MeasureUnit.valueOf(node.get("name").textValue());
        } else {
            throw new IllegalArgumentException("Unable to deserialize measure unit");
        }
    }
}
