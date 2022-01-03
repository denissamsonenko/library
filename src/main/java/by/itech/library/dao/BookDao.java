package by.itech.library.dao;

import by.itech.library.model.Book;
import by.itech.library.model.Genre;

import java.util.List;

public interface BookDao {
    void createBook(Book book) throws DaoException;
    List<Genre> getAllGenre() throws DaoException;
}
