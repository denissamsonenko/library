package by.itech.library.controller.command.impl;

import by.itech.library.controller.command.Command;
import by.itech.library.controller.command.impl.util.MapperWithDate;
import by.itech.library.model.Reader;
import by.itech.library.service.ReaderService;
import by.itech.library.service.ServiceException;
import by.itech.library.service.ServiceProvider;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class SendListReader implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        ReaderService readerService = ServiceProvider.getInstance().getReaderService();

        List<Reader> readerList;
        try {
            readerList = readerService.getAllReader();
        } catch (ServiceException e) {
            throw new ServletException(e);
        }

        String json = MapperWithDate.getInstance()
                .getObjectMapper()
                .writeValueAsString(readerList);
        response.getWriter().write(json);
    }
}