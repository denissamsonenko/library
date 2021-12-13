package by.itech.library.dao;

import by.itech.library.dao.impl.ReaderDaoImpl;

public class DaoProvider {
    private static DaoProvider instance;
    private static final ReaderDao readerDao = new ReaderDaoImpl();

    private DaoProvider() {
    }

    public ReaderDao getReaderDao() {
        return readerDao;
    }

    public static DaoProvider getInstance() {
        if (instance == null) {
            instance = new DaoProvider();
        }
        return instance;
    }
}
