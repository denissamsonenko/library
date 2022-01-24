package by.itech.library.service;

import by.itech.library.model.dto.Book;
import by.itech.library.model.Genre;
import by.itech.library.model.dto.BookDto;
import by.itech.library.model.dto.BookCopyDto;

import java.util.List;

public interface BookService {
    void createBook(Book book) throws ServiceException;

    List<Genre> getAllGenre() throws ServiceException;

    List<BookDto> getAllBook(int limit, int offset, String sort, String sortColumn) throws ServiceException;

    Integer getAllBookCount() throws ServiceException;

    List<BookCopyDto> searchBookByName(String name) throws ServiceException;
}
