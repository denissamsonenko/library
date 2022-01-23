package by.itech.library.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

public class Book implements Serializable {
    private int bookId;
    private String nameRus;
    private String nameOrigin;
    private LocalDate publishDate;
    private BigDecimal price;
    private BigDecimal pricePerDay;
    private int quantity;
    private LocalDate registerDate;
    private int pageNumber;
    private List<BookImage> bookImages;
    private List<Author> authors;
    private List<Genre> genres;
    private List<CopyBook> copyBooks;

    public Book() {
    }

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

    public String getNameOrigin() {
        return nameOrigin;
    }

    public void setNameOrigin(String nameOrigin) {
        this.nameOrigin = nameOrigin;
    }

    public LocalDate getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(LocalDate publishDate) {
        this.publishDate = publishDate;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getPricePerDay() {
        return pricePerDay;
    }

    public void setPricePerDay(BigDecimal pricePerDay) {
        this.pricePerDay = pricePerDay;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public LocalDate getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(LocalDate registerDate) {
        this.registerDate = registerDate;
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    public List<BookImage> getBookImages() {
        return bookImages;
    }

    public void setBookImages(List<BookImage> bookImages) {
        this.bookImages = bookImages;
    }

    public List<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(List<Author> authors) {
        this.authors = authors;
    }

    public List<Genre> getGenres() {
        return genres;
    }

    public void setGenres(List<Genre> genres) {
        this.genres = genres;
    }

    public List<CopyBook> getCopyBooks() {
        return copyBooks;
    }

    public void setCopyBooks(List<CopyBook> copyBooks) {
        this.copyBooks = copyBooks;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return bookId == book.bookId &&
                quantity == book.quantity &&
                pageNumber == book.pageNumber &&
                Objects.equals(nameRus, book.nameRus) &&
                Objects.equals(nameOrigin, book.nameOrigin) &&
                Objects.equals(publishDate, book.publishDate) &&
                Objects.equals(price, book.price) &&
                Objects.equals(pricePerDay, book.pricePerDay) &&
                Objects.equals(registerDate, book.registerDate) &&
                Objects.equals(bookImages, book.bookImages) &&
                Objects.equals(authors, book.authors) &&
                Objects.equals(genres, book.genres) &&
                Objects.equals(copyBooks, book.copyBooks);
    }

    @Override
    public int hashCode() {
        return Objects.hash(bookId, nameRus, nameOrigin, publishDate, price, pricePerDay, quantity, registerDate, pageNumber, bookImages, authors, genres, copyBooks);
    }

    @Override
    public String toString() {
        return "Book{" +
                "bookId=" + bookId +
                ", nameRus='" + nameRus + '\'' +
                ", nameOrigin='" + nameOrigin + '\'' +
                ", publishDate=" + publishDate +
                ", price=" + price +
                ", pricePerDay=" + pricePerDay +
                ", quantity=" + quantity +
                ", registerDate=" + registerDate +
                ", pageNumber=" + pageNumber +
                ", bookImages=" + bookImages +
                ", authors=" + authors +
                ", genres=" + genres +
                ", copyBooks=" + copyBooks +
                '}';
    }
}
