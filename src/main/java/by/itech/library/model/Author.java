package by.itech.library.model;

import java.io.InputStream;
import java.io.Serializable;
import java.util.Objects;

public class Author implements Serializable {
    private int authorId;
    private String authorName;
    private String photoName;
    private InputStream photo;


    public Author() {
    }

    public int getAuthorId() {
        return authorId;
    }

    public void setAuthorId(int authorId) {
        this.authorId = authorId;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public String getPhotoName() {
        return photoName;
    }

    public void setPhotoName(String photoName) {
        this.photoName = photoName;
    }

    public InputStream getPhoto() {
        return photo;
    }

    public void setPhoto(InputStream photo) {
        this.photo = photo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Author author = (Author) o;
        return authorId == author.authorId &&
                Objects.equals(authorName, author.authorName) &&
                Objects.equals(photoName, author.photoName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(authorId, authorName, photoName);
    }

    @Override
    public String toString() {
        return "Author{" +
                "authorId=" + authorId +
                ", authorName='" + authorName + '\'' +
                ", photoName='" + photoName + '\'' +
                '}';
    }
}
