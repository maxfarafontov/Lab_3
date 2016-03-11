package Model;

public class Person {

    long id;
    String name; //имя
    String surname; // фамилия
    int passportSeries; //серия
    int passportNumber; //номер
    String birthday; //др

    public Person(int id, String name, String surname) {
        this.id = id;
        this.name = name;
        this.surname = surname;
    }

    public long getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
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

    public int getPassportSeries() {
        return passportSeries;
    }
    public void setPassportSeries(int passportSeries) {
        this.passportSeries = passportSeries;
    }

    public int getPassportNumber() {
        return passportNumber;
    }
    public void setPassportNumber(int passportNumber) {
        this.passportNumber = passportNumber;
    }

}
