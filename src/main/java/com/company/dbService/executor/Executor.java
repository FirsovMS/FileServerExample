package com.company.dbService.executor;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Executor {
    private final Connection connection;

    public Executor(Connection connection){
        this.connection = connection;
    }

    public void executeUpdate(String query) throws SQLException {
        Statement statement = this.connection.createStatement();
        statement.execute(query);
        statement.close();
    }

    public <T> T executeQuery(String query, ResultHandler<T> handler) throws SQLException{
        Statement statement = connection.createStatement();
        statement.execute(query);
        ResultSet set = statement.getResultSet();
        T result = handler.Handle(set);
        set.close();
        statement.close();
        return result;
    }
}
