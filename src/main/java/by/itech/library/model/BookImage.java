package by.itech.library.model;

import java.io.InputStream;
import java.io.Serializable;
import java.util.Objects;

public class BookImage implements Serializable {
    private int bookId;
    private String bookName;
    private InputStream file;

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public InputStream getFile() {
        return file;
    }

    public void setFile(InputStream file) {
        this.file = file;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BookImage bookImage = (BookImage) o;
        return bookId == bookImage.bookId &&
                Objects.equals(bookName, bookImage.bookName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(bookId, bookName);
    }

    @Override
    public String toString() {
        return "BookImage{" +
                "bookId=" + bookId +
                ", bookName='" + bookName + '\'' +
                '}';
    }
}
