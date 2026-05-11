/*
 * @ (#) ShowService.java     1.0    5/11/2026
 *
 * Copyright (c) 2026 IUH. All rights reserved.
 */
package service;

import entity.Show;

import java.time.LocalDateTime;
import java.util.List;

/*
 * @description
 * @author:NguyenTruong
 * @date:  5/11/2026
 * @version:    1.0
 */
public interface ShowService {
    List<Show> listShowByCurrentDateAndDirector(String director);

    boolean updateShowDateTime(String showId, LocalDateTime newShowDateTime);
}
