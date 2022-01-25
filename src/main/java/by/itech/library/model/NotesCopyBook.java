package by.itech.library.model;

import java.util.Objects;

public class NotesCopyBook {
    private int idNote;
    private String note;
    private int idCopy;

    public NotesCopyBook() {
    }

    public int getIdNote() {
        return idNote;
    }

    public void setIdNote(int idNote) {
        this.idNote = idNote;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public int getIdCopy() {
        return idCopy;
    }

    public void setIdCopy(int idCopy) {
        this.idCopy = idCopy;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NotesCopyBook that = (NotesCopyBook) o;
        return idNote == that.idNote &&
                idCopy == that.idCopy &&
                Objects.equals(note, that.note);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idNote, note, idCopy);
    }

    @Override
    public String toString() {
        return "NotesCopyBook{" +
                "idNote=" + idNote +
                ", note='" + note + '\'' +
                ", idCopy=" + idCopy +
                '}';
    }
}
