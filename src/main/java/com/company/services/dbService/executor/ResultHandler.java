package com.company.services.dbService.executor;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface ResultHandler<T> {
    T Handle(ResultSet set) throws SQLException;
}
