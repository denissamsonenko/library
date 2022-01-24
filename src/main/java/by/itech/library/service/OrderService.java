package by.itech.library.service;

import by.itech.library.model.dto.Order;
import by.itech.library.model.dto.OrderDto;

public interface OrderService {

    void saveOrder(Order order) throws ServiceException;

    OrderDto getOrder(String email) throws ServiceException;
}
