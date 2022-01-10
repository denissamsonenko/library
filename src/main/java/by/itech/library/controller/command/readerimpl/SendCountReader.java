package by.itech.library.controller.command.readerimpl;

import by.itech.library.controller.command.Command;
import by.itech.library.service.ReaderService;
import by.itech.library.service.ServiceException;
import by.itech.library.service.ServiceProvider;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SendCountReader implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        ReaderService readerService = ServiceProvider.getInstance().getReaderService();

        String count;
        try {
            count = String.valueOf(readerService.getCountReader());
        } catch (ServiceException e) {
            throw new ServletException(e);
        }

        response.getWriter().write(count);
    }
}
