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
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        BookService bookService = ServiceProvider.getInstance().getBookService();

        List<BookDto> bookDtoList;
        try {
            bookDtoList = bookService.getAllBook();
        } catch (ServiceException e) {
            throw new ServletException(e);
        }

        String json = MapperWithDate.getInstance()
                .getObjectMapper()
                .writeValueAsString(bookDtoList);
        response.getWriter().write(json);
    }
}
