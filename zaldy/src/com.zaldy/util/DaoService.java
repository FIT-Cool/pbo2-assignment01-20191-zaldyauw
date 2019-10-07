package com.zaldy.util;

import com.zaldy.entity.menu;

import java.sql.SQLException;
import java.util.List;

public interface DaoService<E> {
    List<E> showAll() throws SQLException;

    int addData(E object);
    int deleteData ( E object);
    int updateData(E object);


}
