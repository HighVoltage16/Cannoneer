package cannoneer.highscores;

import java.sql.*;

public class DB {

    private Connection conn;

    public DB(String name) {
        try {
            Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
            System.out.println("Driver successfully loaded");
        } catch (ClassNotFoundException c) {
            System.out.println("Unable to load database driver");
        }

        try {
            conn = DriverManager.getConnection("jdbc:odbc:DRIVER={Microsoft Access Driver (*.mdb)};DBQ=" + name + ".mdb");
            System.out.println("Connection successful");
        } catch (Exception e) {
            System.out.println("Unable to connect to database");
        }
    }

    public ResultSet queryTbl(String sql) throws SQLException {
        Statement s = conn.createStatement();
        ResultSet rs = s.executeQuery(sql);
        return rs;
    }

    public void updateTbl(String update) throws SQLException {
        Statement s = conn.createStatement();
        s.executeUpdate(update);
        s.close();
    }

    public Connection getConnection() {
        return this.conn;
    }

    public void closeDBConnection() {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException ex) {
                System.out.println("Error with database");
            }
        }
    }
}
