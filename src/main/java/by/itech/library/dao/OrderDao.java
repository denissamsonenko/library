package by.itech.library.dao;

import by.itech.library.model.dto.Order;
import by.itech.library.model.dto.OrderDto;

public interface OrderDao {
    void saveOrder(Order order) throws DaoException;

    OrderDto getOrder(String email) throws DaoException;
}
