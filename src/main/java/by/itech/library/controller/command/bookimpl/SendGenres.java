package by.itech.library.controller.command.bookimpl;

import by.itech.library.controller.command.Command;
import by.itech.library.model.Genre;
import by.itech.library.service.BookService;
import by.itech.library.service.ServiceException;
import by.itech.library.service.ServiceProvider;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class SendGenres implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        BookService bookService = ServiceProvider.getInstance().getBookService();

        List<Genre> genres;
        try {
            genres = bookService.getAllGenre();
        } catch (ServiceException e) {
            throw new ServletException(e);
        }

        String json = new ObjectMapper().writeValueAsString(genres);

        response.getWriter().write(json);
    }
}
