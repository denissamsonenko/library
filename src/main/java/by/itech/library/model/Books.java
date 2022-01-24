package by.itech.library.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

public class Books implements Serializable {
    private int bookId;
    private String nameRus;
    private String nameOrigin;
    private LocalDate publishDate;
    private BigDecimal price;
    private BigDecimal pricePerDay;
    private int quantity;
    private LocalDate registerDate;
    private int pageNumber;

    public Books() {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Books books = (Books) o;
        return bookId == books.bookId &&
                quantity == books.quantity &&
                pageNumber == books.pageNumber &&
                Objects.equals(nameRus, books.nameRus) &&
                Objects.equals(nameOrigin, books.nameOrigin) &&
                Objects.equals(publishDate, books.publishDate) &&
                Objects.equals(price, books.price) &&
                Objects.equals(pricePerDay, books.pricePerDay) &&
                Objects.equals(registerDate, books.registerDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(bookId, nameRus, nameOrigin, publishDate, price, pricePerDay, quantity, registerDate, pageNumber);
    }

    @Override
    public String toString() {
        return "Books{" +
                "bookId=" + bookId +
                ", nameRus='" + nameRus + '\'' +
                ", nameOrigin='" + nameOrigin + '\'' +
                ", publishDate=" + publishDate +
                ", price=" + price +
                ", pricePerDay=" + pricePerDay +
                ", quantity=" + quantity +
                ", registerDate=" + registerDate +
                ", pageNumber=" + pageNumber +
                '}';
    }
}
