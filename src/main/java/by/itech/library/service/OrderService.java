package by.itech.library.service;

import by.itech.library.model.CopyBookImg;
import by.itech.library.model.NotesCopyBook;
import by.itech.library.model.Orders;
import by.itech.library.model.dto.Order;
import by.itech.library.model.dto.OrderDto;

import java.util.List;

public interface OrderService {

    void saveOrder(Order order) throws ServiceException;

    OrderDto getOrder(String email) throws ServiceException;

    void closeOrder(Orders orders, List<CopyBookImg> copyBookImg, List<NotesCopyBook> notesCopy) throws ServiceException;
}
