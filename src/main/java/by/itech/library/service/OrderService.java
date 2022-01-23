package by.itech.library.service;

import by.itech.library.model.Order;

public interface OrderService {

    void saveOrder(Order order) throws ServiceException;
}
