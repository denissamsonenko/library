package by.itech.library.model.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

public class BookSearchDto implements Serializable {
    private int idCopy;
    private String nameRus;
    private BigDecimal pricePerDay;

    public int getIdCopy() {
        return idCopy;
    }

    public void setIdCopy(int idCopy) {
        this.idCopy = idCopy;
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
        BookSearchDto that = (BookSearchDto) o;
        return idCopy == that.idCopy &&
                Objects.equals(nameRus, that.nameRus) &&
                Objects.equals(pricePerDay, that.pricePerDay);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idCopy, nameRus, pricePerDay);
    }

    @Override
    public String toString() {
        return "BookSearchDto{" +
                "idCopy=" + idCopy +
                ", nameRus='" + nameRus + '\'' +
                ", pricePerDay=" + pricePerDay +
                '}';
    }
}
