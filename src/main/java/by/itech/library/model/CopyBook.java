package by.itech.library.model;

import java.util.List;
import java.util.Objects;

public class CopyBook {
    private int id;
    private List<String> imageName;

    public CopyBook() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<String> getImageName() {
        return imageName;
    }

    public void setImageName(List<String> imageName) {
        this.imageName = imageName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CopyBook copyBook = (CopyBook) o;
        return id == copyBook.id &&
                imageName.equals(copyBook.imageName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, imageName);
    }

    @Override
    public String toString() {
        return "CopyBook{" +
                "id=" + id +
                ", imageName=" + imageName +
                '}';
    }
}
