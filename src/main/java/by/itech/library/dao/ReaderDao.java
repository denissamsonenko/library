package by.itech.library.dao;

import by.itech.library.model.Reader;

import java.util.List;

public interface ReaderDao {
    void createReader(Reader reader) throws DaoException;

    List<String> getAllEmail() throws DaoException;

    List<Reader> getAllReader(int limit, int offset, String sort) throws DaoException;

    Integer getCountReader() throws DaoException;

    List<Reader> searchReaderByEmail(String email) throws DaoException;
}
