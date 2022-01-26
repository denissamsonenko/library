package by.itech.library.service.Validation;

public class ValidateAuthor {
    private static final int LENGTH_STRING_30 = 30;
    private static final String EMPTY_STRING = "";

    public static boolean isNameCorrect(String authorName) {
        return authorName.length() <= LENGTH_STRING_30 && !authorName.equals(EMPTY_STRING);
    }
}
