/*
 * @ (#) ShowServiceImpl.java     1.0    5/11/2026
 *
 * Copyright (c) 2026 IUH. All rights reserved.
 */
package service.impl;


/*
 * @description
 * @author:NguyenTruong
 * @date:  5/11/2026
 * @version:    1.0
 */

import dao.ShowDAO;
import dao.impl.ShowDAOImpl;
import entity.Show;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

public class ShowServiceImpl implements Serializable, service.ShowService {
    ShowDAO showdao = new ShowDAOImpl();
  @Override
  public List<Show> listShowByCurrentDateAndDirector(String director) {
        return showdao.listShowByCurrentDateAndDirector(director);
    }
    @Override
    public boolean updateShowDateTime(String showId, LocalDateTime newShowDateTime){
      return showdao.updateShowDateTime(showId,newShowDateTime);
    }
}
