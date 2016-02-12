package org.mockenize.vendor.hazelcast.serializer;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hazelcast.nio.ObjectDataInput;
import com.hazelcast.nio.ObjectDataOutput;
import com.hazelcast.nio.serialization.StreamSerializer;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;

/**
 * Created by rwatanabe on 08/02/16.
 */
public abstract class AbstractSerializer<T> implements StreamSerializer<T>{

    @Autowired
    private ObjectMapper objectMapper;

    private Class<? extends T> modelClass;

    public AbstractSerializer(Class<? extends T> modelClass) {
        this.modelClass = modelClass;
    }

    @Override
    public void write(ObjectDataOutput out, Object object) throws IOException {
        out.writeByteArray(objectMapper.writeValueAsBytes(object));
    }

    @Override
    public T read(ObjectDataInput in) throws IOException {
        JsonNode jsonNode = objectMapper.readTree(in.readByteArray());
        return objectMapper.convertValue(jsonNode, modelClass);
    }

    @Override
    public void destroy() {

    }
}
