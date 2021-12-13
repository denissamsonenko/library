package by.itech.library.dao;

import by.itech.library.model.Reader;

public interface ReaderDao {
    void createReader(Reader reader) throws DaoException;
}
