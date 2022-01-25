package by.itech.library.service.impl;

import by.itech.library.dao.DaoException;
import by.itech.library.dao.DaoProvider;
import by.itech.library.dao.OrderDao;
import by.itech.library.model.CopyBookImg;
import by.itech.library.model.NotesCopyBook;
import by.itech.library.model.Orders;
import by.itech.library.model.dto.Order;
import by.itech.library.model.dto.OrderDto;
import by.itech.library.service.OrderService;
import by.itech.library.service.ServiceException;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.List;

import static java.time.temporal.ChronoUnit.DAYS;

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

    @Override
    public OrderDto getOrder(String email) throws ServiceException {
        OrderDto order;

        try {
            order = orderDao.getOrder(email);
            if (order.getOrders().getIdOrder() != 0) {
                calculateTotalPrice(order);
            }
        } catch (DaoException e) {
            throw new ServiceException(e);
        }

        return order;
    }

    @Override
    public void closeOrder(Orders orders, List<CopyBookImg> copyBookImg, List<NotesCopyBook> notesCopy) throws ServiceException {

        try {
            orderDao.closeOrder(orders, copyBookImg, notesCopy);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    private void calculateTotalPrice(OrderDto orderDto) {
        LocalDate localDateToday = LocalDate.now();
        LocalDate dateReturn = orderDto.getOrders().getDateReturn();

        if (localDateToday.isEqual(dateReturn) || localDateToday.isBefore(dateReturn)) {
            BigDecimal advancePrice = orderDto.getOrders().getAdvancePrice();
            orderDto.getOrders().setFinishPrice(advancePrice);
            orderDto.getOrders().setDateExpire(localDateToday);
            orderDto.getOrders().setFine(BigDecimal.ZERO);
        } else {
            BigDecimal priceByTerm = orderDto.getOrders().getAdvancePrice();

            long overdueDays = DAYS.between(dateReturn, localDateToday);

            BigDecimal fee = priceByTerm.multiply(BigDecimal.valueOf(0.01))
                    .multiply(BigDecimal.valueOf(overdueDays))
                    .setScale(2, RoundingMode.HALF_UP);

            BigDecimal totalPrice = priceByTerm
                    .add(fee)
                    .setScale(2, RoundingMode.HALF_UP);

            orderDto.getOrders().setFinishPrice(totalPrice);
            orderDto.getOrders().setDateExpire(localDateToday);
            orderDto.getOrders().setFine(fee);
        }
    }
}
