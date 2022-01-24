package by.itech.library.controller.command.orderimpl;

import by.itech.library.controller.command.Command;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class BookReturnPage implements Command {
    private static final String JSP_BOOK_RETURN_JSP = "jsp/book_return.jsp";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        RequestDispatcher requestDispatcher = request.getRequestDispatcher(JSP_BOOK_RETURN_JSP);
        requestDispatcher.forward(request, response);
    }
}
