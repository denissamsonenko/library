package by.itech.library.service;

import by.itech.library.service.impl.BookServiceImpl;
import by.itech.library.service.impl.OrderServiceImpl;
import by.itech.library.service.impl.ReaderServiceImpl;

public class ServiceProvider {
    private static ServiceProvider instance;
    private static final ReaderService readerService = new ReaderServiceImpl();
    private static final BookService bookService = new BookServiceImpl();
    private static final OrderService orderService = new OrderServiceImpl();

    private ServiceProvider() {
    }

    public OrderService getOrderService() {
        return orderService;
    }

    public ReaderService getReaderService() {
        return readerService;
    }

    public BookService getBookService() {
        return bookService;
    }

    public static ServiceProvider getInstance() {
        if (instance == null) {
            instance = new ServiceProvider();
        }
        return instance;
    }
}
