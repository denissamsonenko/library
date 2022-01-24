package by.itech.library.controller.command.readerimpl;

import by.itech.library.controller.command.Command;
import by.itech.library.controller.util.MapperWithDate;
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

    public static final String OFFSET_ATTR = "offset";
    public static final String LIMIT_ATTR = "limit";
    public static final String SORT_ATTR = "sort";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        ReaderService readerService = ServiceProvider.getInstance().getReaderService();

        int offset = Integer.parseInt(request.getParameter(OFFSET_ATTR));
        int limit = Integer.parseInt(request.getParameter(LIMIT_ATTR));
        String sort = request.getParameter(SORT_ATTR);

        List<Reader> readerList;
        try {
            readerList = readerService.getAllReader(limit, offset, sort);
        } catch (ServiceException e) {
            throw new ServletException(e);
        }

        String json = MapperWithDate.getInstance()
                .getObjectMapper()
                .writeValueAsString(readerList);

        response.getWriter().write(json);

    }
}
