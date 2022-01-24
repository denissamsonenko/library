package by.itech.library.service.impl;

import by.itech.library.dao.BookDao;
import by.itech.library.dao.DaoException;
import by.itech.library.dao.DaoProvider;
import by.itech.library.model.dto.Book;
import by.itech.library.model.Genre;
import by.itech.library.model.dto.BookDto;
import by.itech.library.model.dto.BookCopyDto;
import by.itech.library.service.BookService;
import by.itech.library.service.ServiceException;

import java.util.List;

public class BookServiceImpl implements BookService {
    private final BookDao bookDao = DaoProvider.getInstance().getBookDao();

    @Override
    public void createBook(Book book) throws ServiceException {
        try {
            bookDao.createBook(book);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Genre> getAllGenre() throws ServiceException {
        try {
            return bookDao.getAllGenre();
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<BookDto> getAllBook(int limit, int offset, String sort, String sortColumn) throws ServiceException {
        try {
            return bookDao.getAllBook(limit, offset, sort, sortColumn);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public Integer getAllBookCount() throws ServiceException {
        try {
            return bookDao.getAllBookCount();
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<BookCopyDto> searchBookByName(String name) throws ServiceException {
        try {
            return bookDao.searchBookByName(name);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }
}
