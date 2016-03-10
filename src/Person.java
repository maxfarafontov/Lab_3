import java.util.Date;

public class Person {

    long id;

    String name;
    String surname;

    int passportNumber;
    int passportSeries;

    Date birthday;

    double averageScore;
    long gradebookId;


    public Person(int id, String name, String surname) {
        this.id = id;
        this.name = name;
        this.surname = surname;
    }

    public int getPassportNumber() {
        return passportNumber;
    }

    public void setPassportNumber(int passportNumber) {
        this.passportNumber = passportNumber;
    }

    public long getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPassportSeries() {
        return passportSeries;
    }

    public void setPassportSeries(int passportSeries) {
        this.passportSeries = passportSeries;
    }

    public double getAverageScore() {
        return averageScore;
    }

    public void setAverageScore(double averageScore) {
        this.averageScore = averageScore;
    }

    public long getGradebookId() {
        return gradebookId;
    }

    public void setGradebookId(long gradebookId) {
        this.gradebookId = gradebookId;
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

}
