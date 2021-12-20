package by.itech.library.service.impl;

import by.itech.library.dao.BookDao;
import by.itech.library.dao.DaoException;
import by.itech.library.dao.DaoProvider;
import by.itech.library.model.Book;
import by.itech.library.model.Genre;
import by.itech.library.service.BookService;
import by.itech.library.service.ServiceException;

import java.util.List;

public class BookServiceImpl implements BookService {
    private final BookDao bookDao = DaoProvider.getInstance().getBookDao();

    @Override
    public void createBook(Book book, List<String> pictures, List<Genre> genresId) throws ServiceException {
        try {
            bookDao.createBook(book, pictures, genresId);
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
}
