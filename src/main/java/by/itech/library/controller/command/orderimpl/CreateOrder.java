package by.itech.library.controller.command.orderimpl;

import by.itech.library.controller.command.Command;
import by.itech.library.model.CopyBook;
import by.itech.library.model.OrderStatus;
import by.itech.library.model.Reader;
import by.itech.library.model.dto.Order;
import by.itech.library.service.OrderService;
import by.itech.library.service.ServiceException;
import by.itech.library.service.ServiceProvider;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class CreateOrder implements Command {
    private static final String ADVANCE_PRICE_ATTR = "advancePrice";
    private static final String RETURN_DATE_ATTR = "returnDate";
    private static final String DISCOUNT_ATTR = "discount";
    private static final String READER_ID_ATTR = "readerId";
    private static final String BOOK_ID_ATTR = "bookId";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        OrderService orderService = ServiceProvider.getInstance().getOrderService();

        Order order = new Order();
        order.setOrderStatus(OrderStatus.ACTIVE);
        order.setDateIssue(LocalDate.now());
        order.setAdvancePrice(new BigDecimal(request.getParameter(ADVANCE_PRICE_ATTR)));
        order.setDateReturn(LocalDate.parse(request.getParameter(RETURN_DATE_ATTR)));
        order.setDiscount(Integer.parseInt(request.getParameter(DISCOUNT_ATTR)));
        order.setCopyBooks(getBooks(request));
        Reader reader = new Reader();
        reader.setReaderId(Integer.parseInt(request.getParameter(READER_ID_ATTR)));
        order.setReader(reader);

        try {
            orderService.saveOrder(order);
        } catch (ServiceException e) {
            throw new ServletException(e);
        }

        response.getWriter().write("Successfully saved");
    }

    private List<CopyBook> getBooks(HttpServletRequest request) {
        return Arrays.stream(request.getParameterValues(BOOK_ID_ATTR)).map(value -> {
            CopyBook copyBook = new CopyBook();
            copyBook.setId(Integer.parseInt(value));
            return copyBook;
        }).collect(Collectors.toList());
    }

}
