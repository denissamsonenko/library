package by.itech.library.dao.impl;

import by.itech.library.dao.BookDao;
import by.itech.library.dao.DaoException;
import by.itech.library.dao.pool.PoolConnection;
import by.itech.library.model.Book;
import by.itech.library.model.Genre;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookDaoImpl implements BookDao {
    private final PoolConnection pool = PoolConnection.getInstance();

    private static final String CREATE_BOOK = "INSERT INTO books " +
            "(name_ru, name_origin, publish_date, price, price_per_day, quantity, reg_date, page_number)" +
            " values (?, ?, ?, ?, ?, ?, ?, ?)";

    private static final String BOOK_PICTURE_INSERT = "INSERT INTO book_img (name, id_book) VALUES " +
            "(?, ?)";

    private static final String BOOK_GENRE_INSERT = "INSERT INTO book_genre (id_book, id_genre) " +
            "VALUES (?, ?)";

    private static final String GET_ALL_GENRES = "SELECT * FROM genres";


    @Override
    public void createBook(Book book, List<String> files, List<Genre> genresId) throws DaoException {
        Connection con = null;
        PreparedStatement ps = null;

        try {
            con = pool.getConnection();
            con.setAutoCommit(false);
            ps = con.prepareStatement(CREATE_BOOK);

            ps.setString(1, book.getNameRus());
            ps.setString(2, book.getNameOrigin());
            ps.setBigDecimal(3, book.getPrice());
            ps.setInt(4, book.getQuantity());
            ps.setDate(5, Date.valueOf(book.getPublishDate()));
            ps.setDate(6, Date.valueOf(book.getRegisterDate()));
            ps.setInt(7, book.getPageNumber());
            ps.setBigDecimal(8, book.getPricePerDay());

            int i = ps.executeUpdate(CREATE_BOOK, PreparedStatement.RETURN_GENERATED_KEYS);

            ps = con.prepareStatement(BOOK_PICTURE_INSERT);

            for (String file : files) {
                ps.setString(1, file);
                ps.setInt(2, i);
                ps.execute();
            }

            ps = con.prepareStatement(BOOK_GENRE_INSERT);
            for (Genre genre : genresId) {
                ps.setInt(1, genre.getGenreId());
                ps.setInt(2, i);
                ps.execute();
            }

            con.commit();
            con.setAutoCommit(true);
        } catch (SQLException e) {
            try {
                if (con != null) {
                    con.rollback();
                }
            } catch (SQLException ex) {
                //log
            }
            throw new DaoException(e);
        } finally {
            pool.closeConnection(con, ps);
        }
    }

    @Override
    public List<Genre> getAllGenre() throws DaoException {
        Connection con = null;
        Statement st = null;
        ResultSet rs = null;

        try {
            List<Genre> genres = new ArrayList<>();
            con = pool.getConnection();
            con.setAutoCommit(false);
            st = con.createStatement();
            rs = st.executeQuery(GET_ALL_GENRES);

            while (rs.next()) {
                genres.add(new Genre(
                        rs.getInt("id_genre"),
                        rs.getString("name_genre")));
            }

            con.commit();
            con.setAutoCommit(true);

            return genres;
        } catch (SQLException e) {
            try {
                if (con != null) {
                    con.rollback();
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            throw new DaoException(e);
        } finally {
            pool.closeConnection(con, st, rs);
        }
    }
}
