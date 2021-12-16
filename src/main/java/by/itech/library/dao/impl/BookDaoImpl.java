package by.itech.library.dao.impl;

import by.itech.library.dao.BookDao;
import by.itech.library.dao.DaoException;
import by.itech.library.dao.pool.ConnectionPool;
import by.itech.library.model.Book;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class BookDaoImpl implements BookDao {
    private final ConnectionPool pool = ConnectionPool.getInstance();

    private static final String CREATE_BOOK = "INSERT INTO books " +
            "(name_ru, name_origin, price, book_amount, publish_date, regist_date, page_number, price_per_day)" +
            " values (?, ?, ?, ?, ?, ?, ?, ?)";

    private static final String BOOK_PICTURE_INSERT = "INSERT INTO book_pic (pic_name, book_id) VALUES " +
            "(?, (SELECT book_id FROM books where name_ru=? and name_origin=? " +
            "and price=? and book_amount=? and publish_date=? and regist_date=? " +
            "and page_number=? and price_per_day=?))";

    private static final String BOOK_GENRE_INSERT = "INSERT INTO book_genre (book_id, genre_id) " +
            "VALUES ((SELECT book_id FROM books where name_ru=? and name_origin=? and price=? " +
            "and book_amount=? and publish_date=? and regist_date=? and page_number=? and price_per_day=?), ?)";


    @Override
    public void createBook(Book book) throws DaoException {
        Connection con = null;
        PreparedStatement ps = null;

        try {
            con = pool.takeConnection();
            con.setAutoCommit(false);
            ps = con.prepareStatement(CREATE_BOOK);

            ps.execute();
            ps = con.prepareStatement(BOOK_PICTURE_INSERT);


            ps.execute();
            ps = con.prepareStatement(BOOK_GENRE_INSERT);


            ps.execute();
            con.commit();
        } catch (SQLException e) {
            try {
                con.rollback();
            } catch (SQLException ex) {
                //log
            }
            throw new DaoException(e);
        } finally {
            if (ps != null) {
                pool.closeConnection(con, ps);
            }
        }
    }
}
