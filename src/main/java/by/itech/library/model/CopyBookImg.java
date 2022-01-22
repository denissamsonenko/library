package by.itech.library.model;

import java.util.Objects;

public class CopyBookImg {
    private int idImg;
    private String nameImg;

    public int getIdImg() {
        return idImg;
    }

    public void setIdImg(int idImg) {
        this.idImg = idImg;
    }

    public String getNameImg() {
        return nameImg;
    }

    public void setNameImg(String nameImg) {
        this.nameImg = nameImg;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CopyBookImg that = (CopyBookImg) o;
        return idImg == that.idImg &&
                Objects.equals(nameImg, that.nameImg);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idImg, nameImg);
    }

    @Override
    public String toString() {
        return "CopyBookImg{" +
                "idImg=" + idImg +
                ", nameImg='" + nameImg + '\'' +
                '}';
    }
}
