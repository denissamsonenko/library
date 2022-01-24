package by.itech.library.model;

import java.io.InputStream;
import java.io.Serializable;
import java.util.Objects;

public class CopyBookImg implements Serializable {
    private int idImage;
    private String name;
    private int idCopy;
    private InputStream img;

    public CopyBookImg() {
    }

    public int getIdImage() {
        return idImage;
    }

    public void setIdImage(int idImage) {
        this.idImage = idImage;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getIdCopy() {
        return idCopy;
    }

    public void setIdCopy(int idCopy) {
        this.idCopy = idCopy;
    }

    public InputStream getImg() {
        return img;
    }

    public void setImg(InputStream img) {
        this.img = img;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CopyBookImg that = (CopyBookImg) o;
        return idImage == that.idImage &&
                idCopy == that.idCopy &&
                Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idImage, name, idCopy);
    }

    @Override
    public String toString() {
        return "CopyBookImg{" +
                "idImage=" + idImage +
                ", name='" + name + '\'' +
                ", idCopy=" + idCopy +
                ", img=" + img +
                '}';
    }
}
