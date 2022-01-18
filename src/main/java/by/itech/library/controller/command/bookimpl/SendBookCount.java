package by.itech.library.controller.command.bookimpl;

import by.itech.library.controller.command.Command;
import by.itech.library.service.BookService;
import by.itech.library.service.ReaderService;
import by.itech.library.service.ServiceException;
import by.itech.library.service.ServiceProvider;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SendBookCount implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        BookService bookService = ServiceProvider.getInstance().getBookService();

        String count;
        try {
            count = String.valueOf(bookService.getAllBookCount());
        } catch (ServiceException e) {
            throw new ServletException(e);
        }

        response.getWriter().write(count);
    }
}
