package iuh.fit.infrastructure.mapper;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Map;

public class JacksonDataMapper implements GenericDataMapper{
    private ObjectMapper  mapper;

    public JacksonDataMapper(){
        mapper = new ObjectMapper();
    }
    // chuyển từ object qua map
    @Override
    public Map<String, Object> toMap(Object object) {
        if(object == null){
            return null;
        }
        return mapper.convertValue(object, Map.class);
    }
// chuyển từ map về object
    @Override
    public <T> T toObject(Map<String, Object> data, Class<T> clazz) {
        if(data == null){
            return null;
        }
        return mapper.convertValue(data, clazz);
    }
}
