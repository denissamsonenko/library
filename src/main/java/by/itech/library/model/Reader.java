package by.itech.library.model;

import java.time.LocalDate;

public class Reader {
    private int readerId;
    private String name;
    private String surname;
    private String middleName;
    private String passport;
    private LocalDate birthDate;
    private String email;
    private String city;
    private String address;

    public Reader(int readerId, String name,
                  String surname, String middleName,
                  String passport, LocalDate birthDate,
                  String email, String city, String address) {
        this.readerId = readerId;
        this.name = name;
        this.surname = surname;
        this.middleName = middleName;
        this.passport = passport;
        this.birthDate = birthDate;
        this.email = email;
        this.city = city;
        this.address = address;
    }

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

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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
                ", city='" + city + '\'' +
                ", address='" + address + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Reader reader = (Reader) o;

        if (readerId != reader.readerId) return false;
        if (name != null ? !name.equals(reader.name) : reader.name != null) return false;
        if (surname != null ? !surname.equals(reader.surname) : reader.surname != null) return false;
        if (middleName != null ? !middleName.equals(reader.middleName) : reader.middleName != null) return false;
        if (passport != null ? !passport.equals(reader.passport) : reader.passport != null) return false;
        if (birthDate != null ? !birthDate.equals(reader.birthDate) : reader.birthDate != null) return false;
        if (email != null ? !email.equals(reader.email) : reader.email != null) return false;
        if (city != null ? !city.equals(reader.city) : reader.city != null) return false;
        return address != null ? address.equals(reader.address) : reader.address == null;
    }

    @Override
    public int hashCode() {
        int result = readerId;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (surname != null ? surname.hashCode() : 0);
        result = 31 * result + (middleName != null ? middleName.hashCode() : 0);
        result = 31 * result + (passport != null ? passport.hashCode() : 0);
        result = 31 * result + (birthDate != null ? birthDate.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (city != null ? city.hashCode() : 0);
        result = 31 * result + (address != null ? address.hashCode() : 0);
        return result;
    }
}
