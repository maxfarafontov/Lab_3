package Servlets;

import Model.Person;

import javax.servlet.*;
import java.sql.*;
import java.util.*;
import java.io.*;

public class EditServlet extends GenericServlet{

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
        //sendTableForm(request, response);
        response.setContentType("text/html;charset=utf-8");
    }
}
