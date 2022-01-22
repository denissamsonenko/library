package by.itech.library.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

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


}
