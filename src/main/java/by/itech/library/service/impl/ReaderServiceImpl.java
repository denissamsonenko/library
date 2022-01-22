package by.itech.library.service.impl;

import by.itech.library.dao.DaoException;
import by.itech.library.dao.DaoProvider;
import by.itech.library.dao.ReaderDao;
import by.itech.library.model.Reader;
import by.itech.library.model.dto.ReaderOrder;
import by.itech.library.service.ReaderService;
import by.itech.library.service.ServiceException;

import java.util.List;

public class ReaderServiceImpl implements ReaderService {
    private final ReaderDao readerDao = DaoProvider.getInstance().getReaderDao();

    @Override
    public void createReader(Reader reader) throws ServiceException {
        try {
            readerDao.createReader(reader);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<String> getAllEmail() throws ServiceException {
        try {
            return readerDao.getAllEmail();
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Reader> getAllReader(int limit, int offset, String sort) throws ServiceException {
        try {
            return readerDao.getAllReader(limit, offset, sort);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public Integer getCountReader() throws ServiceException {
        try {
            return readerDao.getCountReader();
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<ReaderOrder> searchReaderBySurname(String surname) throws ServiceException {
        try {
            return readerDao.searchReaderBySurname(surname);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }
}
