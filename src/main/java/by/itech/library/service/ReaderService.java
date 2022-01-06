package by.itech.library.service;

import by.itech.library.model.Reader;

import java.util.List;

public interface ReaderService {
    void createReader(Reader reader) throws ServiceException;

    List<String> getAllEmail() throws ServiceException;
    List<Reader> getAllReader() throws ServiceException;
}
