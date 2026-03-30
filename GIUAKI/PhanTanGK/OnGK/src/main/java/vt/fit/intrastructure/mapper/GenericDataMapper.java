/*
 * @ (#) GenericDataMapper.java     1.0    3/3/2026
 *
 * Copyright (c) 2026 IUH. All rights reserved.
 */
package vt.fit.intrastructure.mapper;

import java.util.Map;

/*
 * @description
 * @author:NguyenTruong
 * @date:  3/3/2026
 * @version:    1.0
 */
public interface GenericDataMapper {
    Map<String,Object>toMap(Object o);
    <T> T fromMap(Map<String,Object> map,Class<T> clazz);
}
