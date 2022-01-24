package by.itech.library.model.dto;

import by.itech.library.model.CopyBook;
import by.itech.library.model.OrderStatus;
import by.itech.library.model.Reader;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

public class Order implements Serializable {
    private int idOrder;
    private LocalDate dateIssue;
    private LocalDate dateReturn;
    private LocalDate dateExpire;
    private BigDecimal advancePrice;
    private BigDecimal finishPrice;
    private int discount;
    private BigDecimal fine;
    private OrderStatus orderStatus;
    private Reader reader;
    private List<CopyBook> copyBooks;

    public Order() {
    }

    public int getIdOrder() {
        return idOrder;
    }

    public void setIdOrder(int idOrder) {
        this.idOrder = idOrder;
    }

    public LocalDate getDateIssue() {
        return dateIssue;
    }

    public void setDateIssue(LocalDate dateIssue) {
        this.dateIssue = dateIssue;
    }

    public LocalDate getDateReturn() {
        return dateReturn;
    }

    public void setDateReturn(LocalDate dateReturn) {
        this.dateReturn = dateReturn;
    }

    public LocalDate getDateExpire() {
        return dateExpire;
    }

    public void setDateExpire(LocalDate dateExpire) {
        this.dateExpire = dateExpire;
    }

    public BigDecimal getAdvancePrice() {
        return advancePrice;
    }

    public void setAdvancePrice(BigDecimal advancePrice) {
        this.advancePrice = advancePrice;
    }

    public BigDecimal getFinishPrice() {
        return finishPrice;
    }

    public void setFinishPrice(BigDecimal finishPrice) {
        this.finishPrice = finishPrice;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    public BigDecimal getFine() {
        return fine;
    }

    public void setFine(BigDecimal fine) {
        this.fine = fine;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Reader getReader() {
        return reader;
    }

    public void setReader(Reader reader) {
        this.reader = reader;
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
        Order order = (Order) o;
        return idOrder == order.idOrder &&
                discount == order.discount &&
                Objects.equals(dateIssue, order.dateIssue) &&
                Objects.equals(dateReturn, order.dateReturn) &&
                Objects.equals(dateExpire, order.dateExpire) &&
                Objects.equals(advancePrice, order.advancePrice) &&
                Objects.equals(finishPrice, order.finishPrice) &&
                Objects.equals(fine, order.fine) &&
                orderStatus == order.orderStatus &&
                Objects.equals(reader, order.reader) &&
                Objects.equals(copyBooks, order.copyBooks);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idOrder, dateIssue, dateReturn, dateExpire, advancePrice, finishPrice, discount, fine, orderStatus, reader, copyBooks);
    }

    @Override
    public String toString() {
        return "Order{" +
                "idOrder=" + idOrder +
                ", dateIssue=" + dateIssue +
                ", dateReturn=" + dateReturn +
                ", dateExpire=" + dateExpire +
                ", advancePrice=" + advancePrice +
                ", finishPrice=" + finishPrice +
                ", discount=" + discount +
                ", fine=" + fine +
                ", orderStatus=" + orderStatus +
                ", reader=" + reader +
                ", copyBooks=" + copyBooks +
                '}';
    }
}
