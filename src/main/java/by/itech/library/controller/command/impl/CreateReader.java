package by.itech.library.controller.command.impl;

import by.itech.library.controller.command.Command;
import by.itech.library.controller.util.CustomMapper;
import by.itech.library.model.Reader;
import by.itech.library.service.ReaderService;
import by.itech.library.service.ServiceException;
import by.itech.library.service.ServiceProvider;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CreateReader implements Command {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        ReaderService readerService = ServiceProvider.getInstance().getReaderService();

        Reader reader = CustomMapper
                .getInstance()
                .getObjectMapper()
                .readValue(request.getReader(), Reader.class);

        try {
            readerService.createReader(reader);
        } catch (ServiceException e) {
            throw new ServletException(e);
        }
    }
}
