package by.itech.library.dao;

import by.itech.library.model.Book;
import by.itech.library.model.Genre;
import by.itech.library.model.dto.BookDto;

import java.util.List;

public interface BookDao {
    void createBook(Book book) throws DaoException;

    List<Genre> getAllGenre() throws DaoException;

    List<BookDto> getAllBook(int limit, int offset, String sort, String sortColumn) throws DaoException;

    Integer getAllBookCount() throws DaoException;
}
