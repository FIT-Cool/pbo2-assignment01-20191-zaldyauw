package com.zaldy.util;

import java.sql.SQLException;
import java.util.List;

public interface DaoService<E> {
    List<E> showAll() throws SQLException;

    int addData(E object);
}
