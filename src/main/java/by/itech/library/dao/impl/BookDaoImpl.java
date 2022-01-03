package by.itech.library.dao.impl;

import by.itech.library.dao.BookDao;
import by.itech.library.dao.DaoException;
import by.itech.library.dao.pool.PoolConnection;
import by.itech.library.model.Author;
import by.itech.library.model.Book;
import by.itech.library.model.CopyBook;
import by.itech.library.model.Genre;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookDaoImpl implements BookDao {
    private final PoolConnection pool = PoolConnection.getInstance();

    private static final String CREATE_BOOK =
            "INSERT INTO books (name_ru, name_origin, publish_date, price, price_per_day, quantity, reg_date, page_number) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

    private static final String BOOK_PICTURE_INSERT = "INSERT INTO book_img (name, id_book) VALUES (?, ?)";

    private static final String BOOK_GENRE_INSERT = "INSERT INTO book_genre (id_book, id_genre) VALUES (?, ?)";

    private static final String AUTHOR_INSERT = "INSERT INTO authors (name, photo) VALUES (?, ?)";

    private static final String BOOK_AUTHOR_INSERT = "INSERT INTO book_author (id_book, id_author) VALUES (?,?)";

    private static final String BOOK_COPY_INSERT = "INSERT INTO book_copy (id_book, status) VALUES (?,?)";

    private static final String GET_ALL_GENRES = "SELECT * FROM genres";

    @Override
    public void createBook(Book book) throws DaoException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            con = pool.getConnection();
            con.setAutoCommit(false);

            ps = con.prepareStatement(CREATE_BOOK, PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setString(1, book.getNameRus());
            ps.setString(2, book.getNameOrigin());
            if (book.getPublishDate() != null) {
                ps.setDate(3, Date.valueOf(book.getPublishDate()));
            } else {
                ps.setNull(3, Types.DATE);
            }
            ps.setBigDecimal(4, book.getPrice());
            ps.setBigDecimal(5, book.getPricePerDay());
            ps.setInt(6, book.getQuantity());
            ps.setDate(7, Date.valueOf(book.getRegisterDate()));
            if (book.getPageNumber() != 0) {
                ps.setInt(8, book.getPageNumber());
            } else {
                ps.setNull(8, Types.INTEGER);
            }
            ps.executeUpdate();
            rs = ps.getGeneratedKeys();
            rs.next();
            int bookId = rs.getInt(1);

            ps = con.prepareStatement(BOOK_PICTURE_INSERT);
            for (String file : book.getImages()) {
                ps.setString(1, file);
                ps.setInt(2, bookId);
                ps.executeUpdate();
            }

            ps = con.prepareStatement(BOOK_GENRE_INSERT);
            for (Genre genre : book.getGenres()) {
                ps.setInt(1, bookId);
                ps.setInt(2, genre.getGenreId());
                ps.executeUpdate();
            }

            List<Integer> authorId = new ArrayList<>();
            ps = con.prepareStatement(AUTHOR_INSERT, PreparedStatement.RETURN_GENERATED_KEYS);
            for (Author author : book.getAuthors()) {
                ps.setString(1, author.getAuthorName());
                if(!author.getPhotoAuthor().equals("")){
                    ps.setString(2, author.getPhotoAuthor());
                } else{
                    ps.setNull(2, Types.VARCHAR);
                }
                ps.executeUpdate();
                rs = ps.getGeneratedKeys();
                if (rs.next()) {
                    authorId.add(rs.getInt(1));
                }
            }

            ps = con.prepareStatement(BOOK_AUTHOR_INSERT);
            for (int author : authorId) {
                ps.setInt(1, bookId);
                ps.setInt(2, author);
                ps.executeUpdate();
            }

            ps = con.prepareStatement(BOOK_COPY_INSERT);
            for (CopyBook copyBook : book.getCopyBooks()) {
                ps.setInt(1, bookId);
                ps.setString(2, copyBook.getStatus());
                ps.executeUpdate();
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
            pool.closeConnection(con, ps, rs);
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
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            throw new DaoException(e);
        } finally {
            pool.closeConnection(con, st, rs);
        }
    }
}
