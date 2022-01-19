package by.itech.library.service;

import by.itech.library.model.Reader;

import java.util.List;

public interface ReaderService {
    void createReader(Reader reader) throws ServiceException;

    List<String> getAllEmail() throws ServiceException;

    List<Reader> getAllReader(int limit, int offset, String sort) throws ServiceException;

    Integer getCountReader() throws ServiceException;

    List<Reader> searchReaderByEmail(String email) throws ServiceException;
}
