package by.itech.library.model.dto;

import by.itech.library.model.OrderStatus;
import by.itech.library.model.Reader;

import java.util.Objects;

public class ReaderOrder {
    private Reader reader;
    private OrderStatus orderStatus;

    public ReaderOrder() {
    }

    public Reader getReader() {
        return reader;
    }

    public void setReader(Reader reader) {
        this.reader = reader;
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
        ReaderOrder that = (ReaderOrder) o;
        return Objects.equals(reader, that.reader) &&
                orderStatus == that.orderStatus;
    }

    @Override
    public int hashCode() {
        return Objects.hash(reader, orderStatus);
    }

    @Override
    public String toString() {
        return "ReaderOrder{" +
                "reader=" + reader +
                ", orderStatus=" + orderStatus +
                '}';
    }
}
