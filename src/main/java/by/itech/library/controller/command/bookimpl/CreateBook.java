package by.itech.library.controller.command.bookimpl;

import by.itech.library.controller.command.Command;
import by.itech.library.model.*;
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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class CreateBook implements Command {
    public static final String SAVE_DIRECTORY_AUTHORS = "/Users/denissamsonenko/iTechArt/src/main/webapp/files/authors/";
    public static final String SAVE_DIRECTORY_BOOKS = "/Users/denissamsonenko/iTechArt/src/main/webapp/files/books/";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        BookService bookService = ServiceProvider.getInstance().getBookService();

        Book book = new Book();
        if (!request.getParameter("nameOrigin").equals("")) {
            book.setNameOrigin(request.getParameter("nameOrigin"));
        }
        if (!request.getParameter("pageNumber").equals("")) {
            book.setPageNumber(Integer.parseInt(request.getParameter("pageNumber")));
        }
        if (!request.getParameter("publishDate").equals("")) {
            book.setPublishDate(LocalDate.parse(request.getParameter("publishDate") + "-01-01"));
        }

        book.setNameRus(request.getParameter("nameRus"));
        book.setRegisterDate(LocalDate.now());
        book.setPrice(BigDecimal.valueOf(Double.parseDouble(request.getParameter("price"))));
        book.setPricePerDay(BigDecimal.valueOf(Double.parseDouble(request.getParameter("pricePerDay"))));
        book.setQuantity(Integer.parseInt(request.getParameter("quantity")));
        book.setImages(getFile(request, "file", SAVE_DIRECTORY_BOOKS));
        book.setAuthors(getAuthors(request));
        book.setGenres(getGenres(request));
        book.setCopyBooks(getCopyBooks(book));

        try {
            bookService.createBook(book);
        } catch (ServiceException e) {
            throw new ServletException(e);
        }
    }

    private List<CopyBook> getCopyBooks(Book book) {
        List<CopyBook> copyBooks = new ArrayList<>();
        for (int i = 0; i < book.getQuantity(); i++) {
            CopyBook copyBook = new CopyBook();
            copyBook.setStatus(String.valueOf(Status.FREE));
            copyBooks.add(copyBook);
        }
        return copyBooks;
    }

    private List<Genre> getGenres(HttpServletRequest request) {
        return Arrays.stream(request.getParameterValues("genre")).map(value -> {
            Genre genre = new Genre();
            genre.setGenreId(Integer.parseInt(value));
            return genre;
        }).collect(Collectors.toList());
    }

    private List<Author> getAuthors(HttpServletRequest request) throws IOException, ServletException {
        List<Author> authors = Arrays.stream(request.getParameterValues("authors"))
                .map(value -> {
                    Author author = new Author();
                    author.setAuthorName(value);
                    return author;
                }).collect(Collectors.toList());

        List<String> authorFiles = getFile(request, "fileAuthor", SAVE_DIRECTORY_AUTHORS);
        for (int i = 0; i < authors.size(); i++) {
            authors.get(i).setPhotoAuthor(authorFiles.get(i));
        }
        return authors;
    }

    private List<String> getFile(HttpServletRequest request, String partName, String saveDirectory) throws IOException, ServletException {
        List<String> picture = new ArrayList<>();
        for (Part part : request.getParts()) {
            if (part.getName().equals(partName)) {
                if (!part.getSubmittedFileName().isEmpty()) {
                    String name = UUID.randomUUID().toString() + "." + part.getSubmittedFileName().trim().replace(" ", "");
                    picture.add(name);
                    part.write(saveDirectory + name);
                } else {
                    picture.add(part.getSubmittedFileName());
                }
            }
        }
        return picture;
    }
}
