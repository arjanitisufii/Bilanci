package datasource;

import java.sql.*;

public class Base {
    private String DB_NAME = "Bilanci.db";
    private String DB_CONNECTION = "jdbc:sqlite:" + DB_NAME;

    private final static Base instance = new Base();
    private Connection connection;
    private Statement statement;
    public boolean open() {
        try {
            connection = DriverManager.getConnection(DB_CONNECTION);
            statement = connection.createStatement();
            return true;
        } catch (SQLException sqlException) {
            System.out.println("Couldn't open database");
            return false;
        }
    }

    public boolean close() {
        try {
            if(statement != null) {
                statement.close();
            }
            if(connection != null) {
                connection.close();
                System.out.println("Successfully closed database");
              return true;
            }
            return false;

        } catch (SQLException sqlException) {
            System.out.println("Couldn't close database");
            return false;
        }
    }

    public static Base getInstance() {
        return instance;
    }

    public Connection getConnection() {
        return connection;
    }

    public Statement getStatement() {
        if (connection != null) {
            try {
                return connection.createStatement();
            } catch (SQLException sqlException) {
                System.out.println("SQLException on getStatement() : " + sqlException.getMessage());
                return null;
            }
        }
        return null;
    }
}
