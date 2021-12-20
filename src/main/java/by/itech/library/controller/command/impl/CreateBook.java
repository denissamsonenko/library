package by.itech.library.controller.command.impl;

import by.itech.library.controller.command.Command;
import by.itech.library.model.Book;
import by.itech.library.model.Genre;
import by.itech.library.service.BookService;
import by.itech.library.service.ServiceException;
import by.itech.library.service.ServiceProvider;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;

public class CreateBook implements Command {
    public static final String SAVE_DIRECTORY = "/Users/denissamsonenko/iTechArt/src/main/webapp/files/";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        BookService bookService = ServiceProvider.getInstance().getBookService();

        List<String> file = getFile(request);

        // replace
        List<Genre> genre = new ArrayList<>();
        genre.add(new Genre());

        Book book = new Book();
        book.setNameRus(request.getParameter("nameRus"));
        book.setNameOrigin(request.getParameter("originName"));
        book.setQuantity(Integer.parseInt(request.getParameter("count")));
        book.setPageNumber(Integer.parseInt(request.getParameter("pageCount")));
        book.setPrice(BigDecimal.valueOf(Float.parseFloat(request.getParameter("price"))));
        book.setPricePerDay(BigDecimal.valueOf(Float.parseFloat(request.getParameter("pricePerDay"))));
        book.setPublishDate(LocalDate.parse(request.getParameter("publishDate")));
        book.setRegisterDate(LocalDate.now());

        try {
            bookService.createBook(book, file, genre);
        } catch (ServiceException e) {
            throw new ServletException(e);
        }
    }

    private List<String> getFile(HttpServletRequest request) throws IOException, ServletException {
        List<String> picture = new ArrayList<>();

        for (Part part : request.getParts()) {
            if (part.getSubmittedFileName() != null) {
                picture.add(part.getSubmittedFileName());
//uuid to avoid collision
                part.write(SAVE_DIRECTORY +
                        (UUID.randomUUID().toString() + "." + part.getSubmittedFileName()));
            }
        }
        return picture;
    }
}
