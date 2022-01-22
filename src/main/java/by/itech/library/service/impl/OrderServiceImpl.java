package by.itech.library.service.impl;

import by.itech.library.dao.DaoProvider;
import by.itech.library.dao.OrderDao;
import by.itech.library.service.OrderService;

public class OrderServiceImpl implements OrderService {
    private final OrderDao orderDao = DaoProvider.getInstance().getOrderDao();
}
