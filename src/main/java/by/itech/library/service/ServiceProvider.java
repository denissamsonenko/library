package by.itech.library.service;

import by.itech.library.service.impl.ReaderServiceImpl;

public class ServiceProvider {
    private static ServiceProvider instance;
    private static final ReaderService readerService = new ReaderServiceImpl();

    private ServiceProvider() {
    }

    public ReaderService getReaderService() {
        return readerService;
    }

    public static ServiceProvider getInstance() {
        if (instance == null) {
            instance = new ServiceProvider();
        }
        return instance;
    }
}
