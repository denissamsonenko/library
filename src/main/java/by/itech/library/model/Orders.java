package by.itech.library.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

public class Orders implements Serializable {
    private int idOrder;
    private int idReader;
    private LocalDate dateIssue;
    private LocalDate dateReturn;
    private LocalDate dateExpire;
    private BigDecimal advancePrice;
    private BigDecimal finishPrice;
    private int discount;
    private BigDecimal fine;
    private OrderStatus orderStatus;

    public Orders() {
    }

    public int getIdOrder() {
        return idOrder;
    }

    public void setIdOrder(int idOrder) {
        this.idOrder = idOrder;
    }

    public int getIdReader() {
        return idReader;
    }

    public void setIdReader(int idReader) {
        this.idReader = idReader;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Orders orders = (Orders) o;
        return idOrder == orders.idOrder &&
                idReader == orders.idReader &&
                discount == orders.discount &&
                Objects.equals(dateIssue, orders.dateIssue) &&
                Objects.equals(dateReturn, orders.dateReturn) &&
                Objects.equals(dateExpire, orders.dateExpire) &&
                Objects.equals(advancePrice, orders.advancePrice) &&
                Objects.equals(finishPrice, orders.finishPrice) &&
                Objects.equals(fine, orders.fine) &&
                orderStatus == orders.orderStatus;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idOrder, idReader, dateIssue, dateReturn, dateExpire, advancePrice, finishPrice, discount, fine, orderStatus);
    }

    @Override
    public String toString() {
        return "Orders{" +
                "idOrder=" + idOrder +
                ", idReader=" + idReader +
                ", dateIssue=" + dateIssue +
                ", dateReturn=" + dateReturn +
                ", dateExpire=" + dateExpire +
                ", advancePrice=" + advancePrice +
                ", finishPrice=" + finishPrice +
                ", discount=" + discount +
                ", fine=" + fine +
                ", orderStatus=" + orderStatus +
                '}';
    }
}
