package by.itech.library.dao.impl;

import by.itech.library.dao.DaoException;
import by.itech.library.dao.ReaderDao;
import by.itech.library.dao.pool.ConnectionBuilder;
import by.itech.library.dao.pool.PoolConnection;
import by.itech.library.model.Reader;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ReaderDaoImpl implements ReaderDao {
    private final ConnectionBuilder pool = new PoolConnection();

    private static final String CREATE_READER = "INSERT INTO readers " +
            "(name, surname, middle_name, passport, birth_date, email, address ) values (?,?,?,?,?,?,?)";

    @Override
    public void createReader(Reader reader) throws DaoException {
        Connection con = null;
        PreparedStatement ps = null;

        try {
            con = pool.getConnection();
            ps = con.prepareStatement(CREATE_READER);

            ps.setString(1, reader.getName());
            ps.setString(2, reader.getSurname());
            ps.setString(3, reader.getMiddleName());
            ps.setString(4, reader.getPassport());
            ps.setDate(5, Date.valueOf(reader.getBirthDate()));
            ps.setString(6, reader.getEmail());
            ps.setString(7, reader.getAddress());

            ps.execute();
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            if (con != null && ps != null) {
                try {
                    con.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
            if(ps !=null){
                try {
                    ps.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        }
    }
}
