package by.itech.library.model;

import java.util.Objects;

public class Author {
    private int authorId;
    private String authorName;
    private String photoAuthor;

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

    public String getPhotoAuthor() {
        return photoAuthor;
    }

    public void setPhotoAuthor(String photoAuthor) {
        this.photoAuthor = photoAuthor;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Author author = (Author) o;
        return authorId == author.authorId &&
                authorName.equals(author.authorName) &&
                photoAuthor.equals(author.photoAuthor);
    }

    @Override
    public int hashCode() {
        return Objects.hash(authorId, authorName, photoAuthor);
    }

    @Override
    public String toString() {
        return "Author{" +
                "authorId=" + authorId +
                ", authorName='" + authorName + '\'' +
                ", photoAuthor='" + photoAuthor + '\'' +
                '}';
    }
}
