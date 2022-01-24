package by.itech.library.dao;

import by.itech.library.model.CopyBookImg;
import by.itech.library.model.NotesCopyBook;
import by.itech.library.model.Orders;
import by.itech.library.model.dto.Order;
import by.itech.library.model.dto.OrderDto;

import java.util.List;

public interface OrderDao {
    void saveOrder(Order order) throws DaoException;

    OrderDto getOrder(String email) throws DaoException;

    void closeOrder(Orders orders, List<CopyBookImg> copyBookImg, List<NotesCopyBook> notesCopy) throws DaoException;
}
