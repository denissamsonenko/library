package by.itech.library.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

public class Reader implements Serializable {
    private int readerId;
    private String name;
    private String surname;
    private String middleName;
    private String passport;
    private LocalDate birthDate;
    private String email;
    private String address;

    public Reader() {
    }

    public int getReaderId() {
        return readerId;
    }

    public void setReaderId(int readerId) {
        this.readerId = readerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getPassport() {
        return passport;
    }

    public void setPassport(String passport) {
        this.passport = passport;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Reader reader = (Reader) o;
        return readerId == reader.readerId &&
                name.equals(reader.name) &&
                surname.equals(reader.surname) &&
                middleName.equals(reader.middleName) &&
                passport.equals(reader.passport) &&
                birthDate.equals(reader.birthDate) &&
                email.equals(reader.email) &&
                address.equals(reader.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(readerId, name, surname, middleName, passport, birthDate, email, address);
    }

    @Override
    public String toString() {
        return "Reader{" +
                "readerId=" + readerId +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", middleName='" + middleName + '\'' +
                ", passport='" + passport + '\'' +
                ", birthDate=" + birthDate +
                ", email='" + email + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
