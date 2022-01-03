package by.itech.library.service;

import by.itech.library.model.Book;
import by.itech.library.model.Genre;

import java.util.List;

public interface BookService {
    void createBook(Book book) throws ServiceException;
    List<Genre> getAllGenre() throws ServiceException;
}
