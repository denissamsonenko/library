package by.itech.library.service;

import by.itech.library.model.Book;

public interface BookService {
    void createBook(Book book) throws ServiceException;
}
