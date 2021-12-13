package by.itech.library.dao.impl;

import by.itech.library.dao.DaoException;
import by.itech.library.dao.ReaderDao;
import by.itech.library.dao.pool.ConnectionPool;
import by.itech.library.model.Reader;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ReaderDaoImpl implements ReaderDao {
    private final ConnectionPool pool = ConnectionPool.getInstance();

    private static final String CREATE_READER = "";

    @Override
    public void createReader(Reader reader) throws DaoException {
        Connection con = null;
        PreparedStatement ps = null;

        try {
            con = pool.takeConnection();
            ps = con.prepareStatement(CREATE_READER);
//
            ps.execute();
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            if (ps != null) {
                pool.closeConnection(con, ps);
            }
        }
    }
}
