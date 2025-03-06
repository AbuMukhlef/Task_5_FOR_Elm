package com.example.employeemanagementsystemv5.common;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.networknt.schema.JsonSchema;
import com.networknt.schema.JsonSchemaFactory;
import com.networknt.schema.SpecVersion;
import com.networknt.schema.ValidationMessage;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.util.Set;

@Component
public class JsonSchemaValidator {

    private final JsonSchema schema;
    private final ObjectMapper objectMapper = new ObjectMapper();

    public JsonSchemaValidator() {
        JsonSchemaFactory schemaFactory = JsonSchemaFactory.getInstance(SpecVersion.VersionFlag.V7);
        InputStream schemaStream = getClass().getResourceAsStream("/schemas/employees-schema.json");
        schema = schemaFactory.getSchema(schemaStream);
    }

    public Set<ValidationMessage> validate(Object obj) {
        JsonNode jsonNode = objectMapper.convertValue(obj, JsonNode.class);
        return schema.validate(jsonNode);
    }
}
