package by.itech.library.model.dto;

import by.itech.library.model.CopyBook;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

public class BookCopyDto implements Serializable {
    private CopyBook copyBooks;
    private String nameRus;
    private BigDecimal pricePerDay;

    public BookCopyDto() {
    }

    public CopyBook getCopyBooks() {
        return copyBooks;
    }

    public void setCopyBooks(CopyBook copyBooks) {
        this.copyBooks = copyBooks;
    }

    public String getNameRus() {
        return nameRus;
    }

    public void setNameRus(String nameRus) {
        this.nameRus = nameRus;
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
        BookCopyDto that = (BookCopyDto) o;
        return Objects.equals(copyBooks, that.copyBooks) &&
                Objects.equals(nameRus, that.nameRus) &&
                Objects.equals(pricePerDay, that.pricePerDay);
    }

    @Override
    public int hashCode() {
        return Objects.hash(copyBooks, nameRus, pricePerDay);
    }

    @Override
    public String toString() {
        return "BookSearchDto{" +
                "copyBooks=" + copyBooks +
                ", nameRus='" + nameRus + '\'' +
                ", pricePerDay=" + pricePerDay +
                '}';
    }
}
