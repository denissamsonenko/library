package by.itech.library.service.Validation;

import by.itech.library.model.dto.Book;

import java.math.BigDecimal;
import java.time.LocalDate;

public class ValidateBook {

    private static final int NAME_LENGTH = 50;
    private static final int PRICE_LENGTH = 13;


    public boolean isCorrect(Book book) {
        return isNameBookCorrect(book.getNameRus()) ||
                isNameOriginCorrect(book.getNameOrigin()) ||
                isPriceCorrect(book.getPrice()) ||
                isPricePerDayCorrect(book.getPricePerDay()) ||
                isQuantityCorrect(book.getQuantity()) ||
                isRegDateCorrect(book.getRegisterDate());

    }

    public static boolean isNameBookCorrect(String nameRu) {
        return nameRu.length() < NAME_LENGTH && !nameRu.isEmpty();
    }

    public static boolean isNameOriginCorrect(String nameOrigin) {
        if (!nameOrigin.isEmpty()) {
            return nameOrigin.length() < NAME_LENGTH;
        }
        return true;
    }

    public static boolean isPriceCorrect(BigDecimal price) {
        return !price.toString().isEmpty() && price.toString().length() <= PRICE_LENGTH;
    }

    public static boolean isPricePerDayCorrect(BigDecimal pricePerDay) {
        return !pricePerDay.toString().isEmpty() && pricePerDay.toString().length() <= PRICE_LENGTH;
    }

    public static boolean isQuantityCorrect(int quantity) {
        return quantity != 0;
    }

    public static boolean isRegDateCorrect(LocalDate date) {
        return !date.toString().isEmpty();
    }
}
