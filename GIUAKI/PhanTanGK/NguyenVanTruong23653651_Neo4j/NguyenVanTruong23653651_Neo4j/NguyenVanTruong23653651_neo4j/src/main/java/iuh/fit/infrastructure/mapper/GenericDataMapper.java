package iuh.fit.infrastructure.mapper;

import iuh.fit.core.entity.Student;

import java.util.Map;

public interface GenericDataMapper {
    Map<String, Object> toMap(Object object);
    <T> T toObject(Map<String, Object> data, Class<T> clazz);
}
