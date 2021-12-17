package by.itech.library.dao.pool;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class PoolConnection implements ConnectionBuilder {

    private DataSource dataSource;

    public PoolConnection() {
        try {
            Context ctx = new InitialContext();
            dataSource = (DataSource) ctx.lookup("java:comp/env/jdbc/library");
        } catch (NamingException e) {
// add logger
        }
    }

    @Override
    public Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }
}
