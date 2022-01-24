package by.itech.library.controller.command.orderimpl;

import by.itech.library.controller.command.Command;
import by.itech.library.controller.util.MapperWithDate;
import by.itech.library.model.dto.OrderDto;
import by.itech.library.service.OrderService;
import by.itech.library.service.ServiceException;
import by.itech.library.service.ServiceProvider;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SendOrder implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        OrderService orderService = ServiceProvider.getInstance().getOrderService();
        String email = request.getParameter("email");

        OrderDto order;
        try {
             order = orderService.getOrder(email);
        } catch (ServiceException e) {
            throw new ServletException(e);
        }

        String json = MapperWithDate
                .getInstance()
                .getObjectMapper()
                .writeValueAsString(order);

        response.getWriter().write(json);
    }
}
