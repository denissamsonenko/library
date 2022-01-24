package by.itech.library.model.dto;

import by.itech.library.model.Orders;
import by.itech.library.model.Reader;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

public class OrderDto implements Serializable {
    private Orders orders;
    private Reader reader;
    private List<BookCopyDto> bookCopyDto;

    public OrderDto() {
    }

    public Orders getOrders() {
        return orders;
    }

    public void setOrders(Orders orders) {
        this.orders = orders;
    }

    public Reader getReader() {
        return reader;
    }

    public void setReader(Reader reader) {
        this.reader = reader;
    }

    public List<BookCopyDto> getBookCopyDto() {
        return bookCopyDto;
    }

    public void setBookCopyDto(List<BookCopyDto> bookCopyDto) {
        this.bookCopyDto = bookCopyDto;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderDto orderDto = (OrderDto) o;
        return Objects.equals(orders, orderDto.orders) &&
                Objects.equals(reader, orderDto.reader) &&
                Objects.equals(bookCopyDto, orderDto.bookCopyDto);
    }

    @Override
    public int hashCode() {
        return Objects.hash(orders, reader, bookCopyDto);
    }

    @Override
    public String toString() {
        return "OrderDto{" +
                "orders=" + orders +
                ", reader=" + reader +
                ", bookCopyDto=" + bookCopyDto +
                '}';
    }
}
