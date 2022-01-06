package by.itech.library.service.impl;

import by.itech.library.dao.DaoException;
import by.itech.library.dao.DaoProvider;
import by.itech.library.dao.ReaderDao;
import by.itech.library.model.Reader;
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
    public List<Reader> getAllReader() throws ServiceException {
        try {
            return readerDao.getAllReader();
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }
}
