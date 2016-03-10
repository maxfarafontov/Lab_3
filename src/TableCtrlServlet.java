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
        PrintWriter w = resp.getWriter();
        w.println("<HTML>");
        w.println("<HEAD>");
        w.print("<script src=/script/js_validators.js></script>");
        w.println("<TITLE>SQL Servlet</TITLE>");
        w.println("</HEAD>");
        w.println("<BODY>");
        w.println("<table border=\"1\">");
        w.println("<caption>Table</caption>");
        w.println("<tr>");
        w.println("<th>Name</th>");
        w.println("<th>Surname</th>");
        w.println("</tr>");

        persons = getFromDb();
        for (Person p : persons) {
            printPerson(p, w);
        }

        w.println("</table>");
        w.println("</BODY>");
        w.println("</HTML>");
        w.close();
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

    private void printPerson(Person p, PrintWriter w) {
        w.println("<tr><td><a href=\"/person?edit=false&id="
                + p.getId() + "\">" + p.getName() + "</a></td><td>"
                + p.getSurname() + "</td></tr>");
    }
}
