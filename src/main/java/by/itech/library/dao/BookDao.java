package by.itech.library.dao;

import by.itech.library.model.Book;

public interface BookDao {
    void createBook(Book book) throws DaoException;
}
