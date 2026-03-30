/*
 * @ (#) JackSonDataMapper.java     1.0    3/3/2026
 *
 * Copyright (c) 2026 IUH. All rights reserved.
 */
package vt.fit.intrastructure.mapper;


/*
 * @description
 * @author:NguyenTruong
 * @date:  3/3/2026
 * @version:    1.0
 */

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tools.jackson.databind.ObjectMapper;

import java.util.Map;


public class JackSonDataMapper implements GenericDataMapper{
    private ObjectMapper mapper;
    public JackSonDataMapper() {
        this.mapper = new ObjectMapper();
    }

    @Override
    public Map<String, Object> toMap(Object o) {
        return mapper.convertValue(o,Map.class);
    }

    @Override
    public <T> T fromMap(Map<String, Object> map, Class<T> clazz) {
        return mapper.convertValue(map,clazz);
    }
}
