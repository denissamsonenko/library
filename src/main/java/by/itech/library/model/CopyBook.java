package by.itech.library.model;

import java.io.InputStream;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

public class CopyBook implements Serializable {
    private int id;
    private List<String> imageName;
    private String status;
    private List<InputStream> image;

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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<InputStream> getImage() {
        return image;
    }

    public void setImage(List<InputStream> image) {
        this.image = image;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CopyBook copyBook = (CopyBook) o;
        return id == copyBook.id &&
                Objects.equals(imageName, copyBook.imageName) &&
                Objects.equals(status, copyBook.status) &&
                Objects.equals(image, copyBook.image);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, imageName, status);
    }

    @Override
    public String toString() {
        return "CopyBook{" +
                "id=" + id +
                ", imageName=" + imageName +
                ", status='" + status + '\'' +
                '}';
    }
}
