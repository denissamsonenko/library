package by.itech.library.controller.command.orderimpl;

import by.itech.library.controller.command.Command;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class OrderPage implements Command {
    private static final String JSP_ORDER_JSP = "jsp/order.jsp";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        RequestDispatcher rd = request.getRequestDispatcher(JSP_ORDER_JSP);
        rd.forward(request, response);
    }
}
