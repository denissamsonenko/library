package by.itech.library.service.Validation;

import java.math.BigDecimal;
import java.time.LocalDate;

public class ValidateOrder {
    private static final String EMPTY_STRING = "";

    public static boolean isDateReturnCorrect(LocalDate returnDate) {
        return !returnDate.toString().equals(EMPTY_STRING);
    }

    public static boolean isIssueDateCorrect(LocalDate issueDate) {
        return !issueDate.toString().equals(EMPTY_STRING);
    }

    public static boolean isAdvancePriceExist(BigDecimal advancePrice) {
        return !advancePrice.toString().equals(EMPTY_STRING);
    }

}
