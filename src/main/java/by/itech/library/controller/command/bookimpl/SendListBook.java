package by.itech.library.controller.command.bookimpl;

import by.itech.library.controller.command.Command;
import by.itech.library.controller.util.MapperWithDate;
import by.itech.library.model.dto.BookDto;
import by.itech.library.service.BookService;
import by.itech.library.service.ServiceException;
import by.itech.library.service.ServiceProvider;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class SendListBook implements Command {

    private static final String SORT_VALUE_ATTR = "sort_value";
    private static final String SORT_ATTR = "sort";
    private static final String OFFSET_ATTR = "offset";
    private static final String LIMIT_ATTR = "limit";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        BookService bookService = ServiceProvider.getInstance().getBookService();

        String sortColumn = request.getParameter(SORT_VALUE_ATTR);
        String sort = request.getParameter(SORT_ATTR);
        int offset = Integer.parseInt(request.getParameter(OFFSET_ATTR));
        int limit = Integer.parseInt(request.getParameter(LIMIT_ATTR));

        List<BookDto> bookDtoList;
        try {
            bookDtoList = bookService.getAllBook(limit, offset, sort, sortColumn);
        } catch (ServiceException e) {
            throw new ServletException(e);
        }

        String json = MapperWithDate.getInstance()
                .getObjectMapper()
                .writeValueAsString(bookDtoList);
        response.getWriter().write(json);
    }
}
