package by.itech.library.service.impl;

import by.itech.library.dao.BookDao;
import by.itech.library.dao.DaoException;
import by.itech.library.dao.DaoProvider;
import by.itech.library.model.Book;
import by.itech.library.service.BookService;
import by.itech.library.service.ServiceException;

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
}
