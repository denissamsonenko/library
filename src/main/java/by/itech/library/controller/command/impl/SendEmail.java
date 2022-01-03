package by.itech.library.controller.command.impl;

import by.itech.library.controller.command.Command;
import by.itech.library.service.ReaderService;
import by.itech.library.service.ServiceException;
import by.itech.library.service.ServiceProvider;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class SendEmail implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        ReaderService readerService = ServiceProvider.getInstance().getReaderService();

        List<String> emails;
        try {
            emails = readerService.getAllEmail();
        } catch (ServiceException e) {
            throw new ServletException(e);
        }

        String json = new ObjectMapper().writeValueAsString(emails);
        response.getWriter().write(json);
    }
}
