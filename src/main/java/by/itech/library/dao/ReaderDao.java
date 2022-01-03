package by.itech.library.dao;

import by.itech.library.model.Reader;

import java.util.List;

public interface ReaderDao {
    void createReader(Reader reader) throws DaoException;
    List<String> getAllEmail() throws  DaoException;
}
