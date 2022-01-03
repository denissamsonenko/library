package by.itech.library.dao.impl;

import by.itech.library.dao.DaoException;
import by.itech.library.dao.ReaderDao;
import by.itech.library.dao.pool.PoolConnection;
import by.itech.library.model.Reader;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReaderDaoImpl implements ReaderDao {
    private final PoolConnection pool = PoolConnection.getInstance();

    private static final String CREATE_READER = "INSERT INTO readers " +
            "(name, surname, middle_name, passport, birth_date, email, address ) values (initcap(?),initcap(?),initcap(?),UPPER(?),?,UPPER(?),?)";
    private static final String GET_ALL_EMAIL = "SELECT email FROM readers";

    @Override
    public void createReader(Reader reader) throws DaoException {
        Connection con = null;
        PreparedStatement ps = null;

        try {
            con = pool.getConnection();
            con.setAutoCommit(false);

            ps = con.prepareStatement(CREATE_READER);

            ps.setString(1, reader.getName());
            ps.setString(2, reader.getSurname());
            ps.setString(3, reader.getMiddleName());
            ps.setString(4, reader.getPassport());
            ps.setDate(5, Date.valueOf(reader.getBirthDate()));
            ps.setString(6, reader.getEmail());
            ps.setString(7, reader.getAddress());

            ps.execute();
            con.commit();
            con.setAutoCommit(true);
        } catch (SQLException e) {
            pool.rollback(con);
            throw new DaoException(e);
        } finally {
            pool.closeConnection(con, ps);
        }
    }

    @Override
    public List<String> getAllEmail() throws DaoException {
        Connection con = null;
        Statement st = null;
        ResultSet rs = null;

        try {

            List<String> emails = new ArrayList<>();
            con = pool.getConnection();
            con.setAutoCommit(false);
            st = con.createStatement();
            rs = st.executeQuery(GET_ALL_EMAIL);

            while (rs.next()) {
                emails.add(rs.getString("email"));
            }

            con.commit();
            con.setAutoCommit(true);

            return emails;
        } catch (SQLException e) {
            pool.rollback(con);
            throw new DaoException(e);
        } finally {
            pool.closeConnection(con, st, rs);
        }
    }
}
