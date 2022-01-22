package by.itech.library.dao;

import by.itech.library.dao.impl.BookDaoImpl;
import by.itech.library.dao.impl.OrderDaoImpl;
import by.itech.library.dao.impl.ReaderDaoImpl;

public class DaoProvider {
    private static DaoProvider instance;
    private static final ReaderDao readerDao = new ReaderDaoImpl();
    private static final BookDao bookDao = new BookDaoImpl();
    private static final OrderDao orderDao = new OrderDaoImpl();

    private DaoProvider() {
    }

    public OrderDao getOrderDao() {
        return orderDao;
    }

    public ReaderDao getReaderDao() {
        return readerDao;
    }

    public BookDao getBookDao() {
        return bookDao;
    }

    public static DaoProvider getInstance() {
        if (instance == null) {
            instance = new DaoProvider();
        }
        return instance;
    }
}
