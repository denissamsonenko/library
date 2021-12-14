package by.itech.library.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

public class Book {
    private int bookId;
    private String nameRus;
    private String nameOrigin;
    private BigDecimal price;
    private int bookAmount;
    private LocalDate publishDate;
    private LocalDate registerDate;
    private int pageNumber;
    private BigDecimal pricePerDay;

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

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public int getBookAmount() {
        return bookAmount;
    }

    public void setBookAmount(int bookAmount) {
        this.bookAmount = bookAmount;
    }

    public LocalDate getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(LocalDate publishDate) {
        this.publishDate = publishDate;
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

    public BigDecimal getPricePerDay() {
        return pricePerDay;
    }

    public void setPricePerDay(BigDecimal pricePerDay) {
        this.pricePerDay = pricePerDay;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return bookId == book.bookId &&
                bookAmount == book.bookAmount &&
                pageNumber == book.pageNumber &&
                nameRus.equals(book.nameRus) &&
                nameOrigin.equals(book.nameOrigin) &&
                price.equals(book.price) &&
                publishDate.equals(book.publishDate) &&
                registerDate.equals(book.registerDate) &&
                pricePerDay.equals(book.pricePerDay);
    }

    @Override
    public int hashCode() {
        return Objects.hash(bookId, nameRus, nameOrigin, price, bookAmount, publishDate, registerDate, pageNumber, pricePerDay);
    }

    @Override
    public String toString() {
        return "Book{" +
                "bookId=" + bookId +
                ", nameRus='" + nameRus + '\'' +
                ", nameOrigin='" + nameOrigin + '\'' +
                ", price=" + price +
                ", bookAmount=" + bookAmount +
                ", publishDate=" + publishDate +
                ", registerDate=" + registerDate +
                ", pageNumber=" + pageNumber +
                ", pricePerDay=" + pricePerDay +
                '}';
    }
}
