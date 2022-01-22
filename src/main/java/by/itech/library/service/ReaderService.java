package by.itech.library.service;

import by.itech.library.model.Reader;
import by.itech.library.model.dto.ReaderOrder;

import java.util.List;

public interface ReaderService {
    void createReader(Reader reader) throws ServiceException;

    List<String> getAllEmail() throws ServiceException;

    List<Reader> getAllReader(int limit, int offset, String sort) throws ServiceException;

    Integer getCountReader() throws ServiceException;

    List<ReaderOrder> searchReaderBySurname(String surname) throws ServiceException;
}
