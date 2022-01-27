package by.itech.library.controller.command.bookimpl;

import by.itech.library.controller.command.Command;
import by.itech.library.model.*;
import by.itech.library.model.dto.Book;
import by.itech.library.service.BookService;
import by.itech.library.service.ServiceException;
import by.itech.library.service.ServiceProvider;
import by.itech.library.service.ValidationException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class CreateBook implements Command {
    private static final String NAME_ORIGIN = "nameOrigin";
    private static final String EMPTY_VALUE = "";
    private static final String PAGE_NUMBER_ATTR = "pageNumber";
    private static final String PUBLISH_DATE_ATTR = "publishDate";
    private static final String NAME_RUS_ATTR = "nameRus";
    private static final String PRICE_ATTR = "price";
    private static final String PRICE_PER_DAY_ATTR = "pricePerDay";
    private static final String QUANTITY_ATTR = "quantity";
    private static final String GENRE_ATTR = "genre";
    private static final String AUTHORS_ATTR = "authors";
    private static final String FILE_AUTHOR_ATTR = "fileAuthor";
    private static final String FILE_ATTR_IMG = "file";
    public static final String BOOK_SAVED_RESPONSE = "Book successfully saved";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        BookService bookService = ServiceProvider.getInstance().getBookService();

        Book book = new Book();
        if (!request.getParameter(NAME_ORIGIN).equals(EMPTY_VALUE)) {
            book.setNameOrigin(request.getParameter(NAME_ORIGIN));
        }
        if (!request.getParameter(PAGE_NUMBER_ATTR).equals(EMPTY_VALUE)) {
            book.setPageNumber(Integer.parseInt(request.getParameter(PAGE_NUMBER_ATTR)));
        }
        if (!request.getParameter(PUBLISH_DATE_ATTR).equals(EMPTY_VALUE)) {
            book.setPublishDate(LocalDate.parse(request.getParameter(PUBLISH_DATE_ATTR) + "-01-01"));
        }

        book.setNameRus(request.getParameter(NAME_RUS_ATTR));
        book.setRegisterDate(LocalDate.now());
        book.setPrice(BigDecimal.valueOf(Double.parseDouble(request.getParameter(PRICE_ATTR))));
        book.setPricePerDay(BigDecimal.valueOf(Double.parseDouble(request.getParameter(PRICE_PER_DAY_ATTR))));
        book.setQuantity(Integer.parseInt(request.getParameter(QUANTITY_ATTR)));
        book.setBookImages(getFile(request));
        book.setAuthors(getAuthors(request));
        book.setGenres(getGenres(request));
        book.setCopyBooks(getCopyBooks(book));

        try {
            bookService.createBook(book);
            response.getWriter().write(BOOK_SAVED_RESPONSE);
        } catch (ServiceException e) {
            throw new ServletException(e);
        } catch (ValidationException e) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().write(e.getMessage());
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
        return Arrays.stream(request.getParameterValues(GENRE_ATTR)).map(value -> {
            Genre genre = new Genre();
            genre.setGenreId(Integer.parseInt(value));
            return genre;
        }).collect(Collectors.toList());
    }

    private List<Author> getAuthors(HttpServletRequest request) throws IOException, ServletException {
        List<Author> authors = Arrays.stream(request.getParameterValues(AUTHORS_ATTR))
                .map(value -> {
                    Author author = new Author();
                    author.setAuthorName(value);
                    return author;
                }).collect(Collectors.toList());


        List<Author> authorFiles = getFileAuthor(request);

        for (int i = 0; i < authors.size(); i++) {
            authors.get(i).setPhotoName(authorFiles.get(i).getPhotoName());
            authors.get(i).setPhoto(authorFiles.get(i).getPhoto());
        }
        return authors;
    }

    private List<Author> getFileAuthor(HttpServletRequest request) throws IOException, ServletException {
        List<Author> picture = new ArrayList<>();
        for (Part part : request.getParts()) {
            if (part.getName().equals(FILE_AUTHOR_ATTR)) {
                if (!part.getSubmittedFileName().isEmpty()) {
                    Author author = new Author();
                    String name = UUID.randomUUID().toString() + "." + part.getSubmittedFileName().trim().replace(" ", EMPTY_VALUE);
                    author.setPhotoName(name);
                    InputStream inputStream = part.getInputStream();
                    author.setPhoto(inputStream);
                    picture.add(author);
                } else {
                    Author author = new Author();
                    author.setPhotoName(part.getSubmittedFileName());
                    picture.add(author);
                }
            }
        }
        return picture;
    }

    private List<BookImage> getFile(HttpServletRequest request) throws IOException, ServletException {
        List<BookImage> picture = new ArrayList<>();
        for (Part part : request.getParts()) {
            if (part.getName().equals(FILE_ATTR_IMG)) {
                BookImage bookImage = new BookImage();
                String name = UUID.randomUUID().toString() + "." + part.getSubmittedFileName().trim().replace(" ", EMPTY_VALUE);
                InputStream inputStream = part.getInputStream();
                bookImage.setFile(inputStream);
                bookImage.setBookName(name);
                picture.add(bookImage);
            }
        }
        return picture;
    }
}
