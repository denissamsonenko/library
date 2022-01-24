package by.itech.library.dao;

import by.itech.library.model.Order;

public interface OrderDao {
    void saveOrder(Order order) throws DaoException;

    Order getOrder() throws DaoException;
}
