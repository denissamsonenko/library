package by.itech.library.controller.command.impl;

import by.itech.library.controller.command.Command;
import by.itech.library.controller.util.CustomMapper;
import by.itech.library.model.Genre;
import by.itech.library.service.BookService;
import by.itech.library.service.ServiceException;
import by.itech.library.service.ServiceProvider;

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

        String json = CustomMapper
                .getInstance()
                .getObjectMapper()
                .writeValueAsString(genres);

        response.setHeader("Access-Control-Allow-Origin", "*");
        response.getWriter().write(json);
    }
}
