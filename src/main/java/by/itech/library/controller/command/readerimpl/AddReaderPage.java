package by.itech.library.controller.command.readerimpl;

import by.itech.library.controller.command.Command;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AddReaderPage implements Command {
    private static final String JSP_READER_JSP = "jsp/reader.jsp";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        RequestDispatcher requestDispatcher = request.getRequestDispatcher(JSP_READER_JSP);
        requestDispatcher.forward(request, response);
    }
}
