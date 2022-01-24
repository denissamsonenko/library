package by.itech.library.service.impl;

import by.itech.library.dao.DaoException;
import by.itech.library.dao.DaoProvider;
import by.itech.library.dao.OrderDao;
import by.itech.library.model.dto.Order;
import by.itech.library.service.OrderService;
import by.itech.library.service.ServiceException;

public class OrderServiceImpl implements OrderService {
    private final OrderDao orderDao = DaoProvider.getInstance().getOrderDao();

    @Override
    public void saveOrder(Order order) throws ServiceException {
        try {
            orderDao.saveOrder(order);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }
}
