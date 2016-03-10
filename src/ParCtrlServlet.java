import javax.servlet.*;
import java.sql.*;
import java.util.*;
import java.io.*;

public class ParCtrlServlet extends GenericServlet {

    @Override
    public void service(ServletRequest request, ServletResponse response) throws ServletException, IOException {
        sendParamForm(request, response);
    }

    private void sendParamForm(ServletRequest request, ServletResponse response) throws ServletException, IOException {
        if ("true".equals(request.getParameter("edit"))) {
            sendEditForm(request, response);
        } else {
            sendViewForm(request, response);
        }
    }

    private void sendViewForm(ServletRequest request, ServletResponse response) throws ServletException, IOException {
        PrintWriter w = response.getWriter();
        w.println("<HTML>");
        w.println("<HEAD>");
        w.print("<script src=/script/js_validators.js></script>");
        w.println("<TITLE>SQL Servlet</TITLE>");
        w.println("</HEAD>");
        w.println("<BODY>");
        printEditButton(request.getParameter("id"),w);
        w.println("<table border=\"1\">");
        w.println("<caption>Person</caption>");


        Map<String, String> params = getFromDb(Integer.parseInt(request.getParameter("id")));
        int i=0;
        for(String key:params.keySet()) {

            printViewCell(w, key, params.get(key));

            if ((i % 2) != 0) {
                w.println("<tr>");
            } else {
                w.println("<th>&nbsp;</th>");
            }
            i++;
        }

        w.println("</table>");

        w.println("</BODY>");
        w.println("</HTML>");
    }


    private Map<String, String> getFromDb(int id) {
        try{
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/db_first", "root", "P@ssw0rd");
            Statement st = con.createStatement();


            ResultSet rs = st.executeQuery("select name,surname,number, series,birthday,score, book_id from person where id = " + id);


            Map<String, String> ps = new HashMap();
            while(rs.next()) {
                for(int i=1;i<=rs.getMetaData().getColumnCount();i++) {
                    ps.put(rs.getMetaData().getColumnName(i),rs.getString(i));
                }


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


    private void printEditButton(String id, PrintWriter w) {
        w.println("<tr><td><a href=\"/person?edit=true&id=" + id + "\">Edit</a>");
    }

    private void printSaveButton(String id, PrintWriter w) {
        w.println("<tr><td><a href=\"/person?edit=true&id=" + id + "\">Save</a>");
    }

    private void printViewCell(PrintWriter w, String name, String value) {
        w.println("<td>" + name + "</td><td>" + value + "</td>");
    }

    private void sendEditForm(ServletRequest request, ServletResponse response) throws ServletException, IOException{
        PrintWriter w = response.getWriter();
        w.println("<HTML>");
        w.println("<HEAD>");
        w.print("<script src=\"/script/js_validators.js\"></script>");
        w.println("<TITLE>SQL Servlet</TITLE>");
        w.println("</HEAD>");
        w.println("<BODY>");
        String id = request.getParameter("id");
        w.println("<FORM ACTION=\"/save\" METHOD=\"GET\">");
        w.println("<input type=\"submit\" value=\"send\" id=\"save\"/>");
        w.println("<input type=\"hidden\" name=\"id\" value=" + id + ">");
        w.println("<table border=\"1\">");
        w.println("<caption>Person</caption>");

        Map<String, String> params = getFromDb(Integer.parseInt(request.getParameter("id")));
        int i=0;
        for(String key:params.keySet()) {

            printEditCell(w, key, params.get(key));

            if ((i % 2) != 0) {
                w.println("<tr>");
            } else {
                w.println("<th>&nbsp;</th>");
            }
            i++;
        }

        w.println("</table>");
        w.println("</BODY>");
        w.println("</HTML>");
    }

    private void printEditCell(PrintWriter w, String name, String value) {
        w.println("<td>" + name + "</td><td><input type=\"text\" onchange=\"validate_name(this, this.form)\" value= " + value + " name=" + name + " \\></td>");
    }
}
