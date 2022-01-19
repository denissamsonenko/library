package by.itech.library.model.dto;

import by.itech.library.model.Genre;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

public class BookDto implements Serializable {
    private int bookId;
    private String nameRus;
    private LocalDate publishDate;
    private int quantity;
    private List<Genre> genres;
    private int availableBook;

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public String getNameRus() {
        return nameRus;
    }

    public void setNameRus(String nameRus) {
        this.nameRus = nameRus;
    }

    public LocalDate getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(LocalDate publishDate) {
        this.publishDate = publishDate;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public List<Genre> getGenres() {
        return genres;
    }

    public void setGenres(List<Genre> genres) {
        this.genres = genres;
    }

    public int getAvailableBook() {
        return availableBook;
    }

    public void setAvailableBook(int availableBook) {
        this.availableBook = availableBook;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BookDto bookDto = (BookDto) o;
        return bookId == bookDto.bookId &&
                quantity == bookDto.quantity &&
                availableBook == bookDto.availableBook &&
                Objects.equals(nameRus, bookDto.nameRus) &&
                Objects.equals(publishDate, bookDto.publishDate) &&
                Objects.equals(genres, bookDto.genres);
    }

    @Override
    public int hashCode() {
        return Objects.hash(bookId, nameRus, publishDate, quantity, genres, availableBook);
    }

    @Override
    public String toString() {
        return "BookDto{" +
                "bookId=" + bookId +
                ", nameRus='" + nameRus + '\'' +
                ", publishDate=" + publishDate +
                ", quantity=" + quantity +
                ", genres=" + genres +
                ", availableBook=" + availableBook +
                '}';
    }
}
