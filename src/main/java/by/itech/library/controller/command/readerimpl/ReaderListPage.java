package by.itech.library.controller.command.readerimpl;

import by.itech.library.controller.command.Command;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ReaderListPage implements Command {
    private static final String JSP_READER_LIST_JSP = "jsp/readerList.jsp";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        RequestDispatcher rd = request.getRequestDispatcher(JSP_READER_LIST_JSP);
        rd.forward(request, response);
    }
}
