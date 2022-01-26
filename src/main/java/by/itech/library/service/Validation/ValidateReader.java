package by.itech.library.service.Validation;

import java.util.List;

public class ValidateReader {
    private static final String EMPTY_STRING = "";
    private static final int LENGTH_STRING_50 = 50;
    private static final int LENGTH_STRING_30 = 30;

    public static boolean isNameCorrect(String name) {
        return name.length() <= LENGTH_STRING_30 && !name.equals(EMPTY_STRING);
    }

    public static boolean isSurnameCorrect(String surname) {
        return surname.length() <= LENGTH_STRING_30 && !surname.equals(EMPTY_STRING);
    }

    public static boolean isMidNameCorrect(String midName) {
        if (midName != null) {
            return midName.length() <= LENGTH_STRING_30;
        } else {
            return true;
        }
    }

    public static boolean isPassportCorrect(String passport) {
        if (passport != null) {
            return passport.length() <= LENGTH_STRING_30;
        } else {
            return true;
        }
    }

    public static boolean isAddressCorrect(String address) {
        if (address != null) {
            return address.length() <= LENGTH_STRING_50;
        } else {
            return true;
        }
    }

    public static boolean isEmailCorrect(String email, List<String> emails) {
        return !emails.contains(email)
                && email.length() <= LENGTH_STRING_50
                && !email.equals(EMPTY_STRING);
    }
}
