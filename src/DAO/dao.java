package DAO;

import Model.Person;
import java.sql.*;
import java.util.*;


public class dao {
    public static final String URL = "jdbc:mysql://localhost:3306/db_first";
    public static final String USERNAME = "root";
    public static final String PASSWORD = "19941124";
    public List<Person> Persons = new ArrayList();
    private Connection connection;
    private Statement st;

    public dao() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public List<Person> getPersons() {
        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            st = connection.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        String sql = "SELECT * FROM person";
        try {
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                Persons.add(new Person(rs.getInt("id"), rs.getString("name"), rs.getString("surname")));
            }
            rs.close();
        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (st != null)
                    connection.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
            try {
                if (connection != null)
                    connection.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
        return Persons;
    }

    public void deletePerson(int id) {
        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            st = connection.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        String q = "DELETE from users where id =" + id;
        try {
            st.executeUpdate(q);
        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (st != null)
                    connection.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
            try {
                if (connection != null)
                    connection.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
    }

    public Person getUserById(int id) {
        String q = "select id,name,surname,email,age,passportSeries,passportNumber from users where id=" + id;
        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            st = connection.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            ResultSet rs = st.executeQuery(q);
            if (rs.next()) {
                Person user = new Person(rs.getInt("id"), rs.getString("name"), rs.getString("surname"));
                return user;
            }
            rs.close();
        } catch (SQLException e) {
        }
        return null;
    }

    public void addUser(Person person) {
        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            st = connection.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        String q = "insert into persons (id,name,surname) values " + person.toString() + ";";
        try {
            st.executeUpdate(q);
        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (st != null)
                    connection.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
            try {
                if (connection != null)
                    connection.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
    }

    public void updateUser(Person person) {
        String q = "Update users set name ='" + person.getName() + "',surname='" + person.getSurname();
        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            st = connection.createStatement();
            st.executeUpdate(q);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (st != null)
                    connection.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
            try {
                if (connection != null)
                    connection.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
    }
}
