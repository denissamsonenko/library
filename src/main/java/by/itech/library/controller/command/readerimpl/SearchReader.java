package by.itech.library.controller.command.readerimpl;

import by.itech.library.controller.command.Command;
import by.itech.library.controller.util.MapperWithDate;
import by.itech.library.model.Reader;
import by.itech.library.model.dto.ReaderOrder;
import by.itech.library.service.ReaderService;
import by.itech.library.service.ServiceException;
import by.itech.library.service.ServiceProvider;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class SearchReader implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        ReaderService readerService = ServiceProvider.getInstance().getReaderService();

        String surname = request.getParameter("surname");

        List<ReaderOrder> readerOrder;
        try {
            readerOrder = readerService.searchReaderBySurname(surname);
        } catch (ServiceException e) {
            throw new ServletException(e);
        }

        String json = MapperWithDate
                .getInstance()
                .getObjectMapper()
                .writeValueAsString(readerOrder);

        response.getWriter().write(json);
    }
}
