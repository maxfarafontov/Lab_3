package Servlets;

import Model.Person;

import javax.servlet.*;
import java.sql.*;
import java.util.*;
import java.io.*;

public class TableCtrlServlet extends GenericServlet {


    List<Person> persons = new ArrayList();

    @Override
    public void init(ServletConfig conf) {

        try{
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            System.out.println(ex.toString());
        }
    }

    @Override
    public void service(ServletRequest request, ServletResponse response) throws ServletException, IOException {
        sendTableForm(request, response);
        response.setContentType("text/html;charset=utf-8");
    }

    private void sendTableForm(ServletRequest req, ServletResponse resp) throws ServletException, IOException {
        PrintWriter pw = resp.getWriter();
        pw.println("<HTML>");
        pw.println("<HEAD>");
        pw.println("<script src=/script/js_validators.js></script>");
        pw.println("<TITLE>SQL Servlet</TITLE>");

        //global style
        pw.println("<style type=\"text/css\">");
        pw.println("table {border-collapse: separate;}");
        pw.println("th {border: 1px solid grey;}");
        pw.println("td {border: 1px solid grey;}");
        pw.println("</style>");
        //end

        pw.println("</HEAD>");
        pw.println("<BODY>");
        pw.println("<a href=\"/edit\">Edit</a><br>");
        pw.println("<a href=\"/add\">Add</a><br>");
        pw.println("<table>");
        pw.println("<caption>Table</caption>");
        pw.println("<tr>");
        pw.println("<th>ID</th>");
        pw.println("<th>Name</th>");
        pw.println("<th>Surname</th>");
        pw.println("</tr>");

        persons = getFromDb();
        for (Person p : persons) {
            printPerson(p, pw);
        }

        pw.println("</table>");
        pw.println("</BODY>");
        pw.println("</HTML>");
        pw.close();
    }

    private List<Person> getFromDb() {
        try{
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/db_first", "root", "19941124");
            Statement st = con.createStatement();

            ResultSet rs = st.executeQuery("select id,name,surname from person;");

            List<Person> ps = new ArrayList();
            while(rs.next()) {
                ps.add(new Person(Integer.parseInt(rs.getString(1)),rs.getString(2),rs.getString(3)));
            }

            rs.close();
            st.close();
            con.close();
            return ps;
        } catch (SQLException ex) {
            ex.toString();
        }
        return null;
    }

    private void printPerson(Person p, PrintWriter pw) {
        pw.println("<tr><th><a href=/person?edit=false&id="
                + p.getId() + ">" + p.getId() + "</a></th><th>" + p.getName() + "</th><th>"
                + p.getSurname() + "</th></tr>");
    }
}
