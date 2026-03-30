package com.ngviet291.mapper;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.lang.Object;
import java.util.Map;

public class DataMapper {
    private ObjectMapper objectMapper= new ObjectMapper();
    public Map<String, Object> toMap(Object object){
        return objectMapper.convertValue(object, Map.class);
    }
    public <T> T toObject(Map<String,Object> obj,Class<T> T){
        return objectMapper.convertValue(obj,T);
    }
}
