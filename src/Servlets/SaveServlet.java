package Servlets;

import javax.servlet.*;
import java.sql.*;
import java.util.*;
import java.io.*;

public class SaveServlet  extends GenericServlet {

    @Override
    public void service(ServletRequest request, ServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        Map<String, String[]> params = request.getParameterMap();
        String id = params.get("id")[0];

        setToDb(params);

        ServletContext context = getServletContext();
        RequestDispatcher dispatcher = context.getRequestDispatcher("/person?id=" + id + "&edit=false");
        dispatcher.forward(request,response);
    }

    private int setToDb(Map<String, String[]> params) {
        try{
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/db_first", "root", "19941124 ");
            Statement st = con.createStatement();

            String query = "update person set name = '" + params.get("name")[0] + "', surname = '" + params.get("surname")[0] + "' where id = " + params.get("id")[0];
            int s =  st.executeUpdate(query);

            st.close();
            con.close();

            return s;
        }
        catch (SQLException ex) {
            ex.toString();
        }
        return 0;
    }
}
