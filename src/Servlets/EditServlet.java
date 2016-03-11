package Servlets;

import Model.Person;
import DAO.dao;

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
        if (request.getParameterMap().isEmpty()) {
            request.getRequestDispatcher("edit.jsp").forward(request, response);
        }
        String action = request.getParameter("action");
        /*
        if (action != null) {
            switch (action) {
                case "edit":
                    request.setAttribute("user", new dao().getUserById(Integer.parseInt(request.getParameter("id"))));
                    request.getRequestDispatcher("edit.jsp").forward(request, response);
                    break;
                case "save":
                    Person person;
                    //Integer id = request.getParameter("id");
                    String name = request.getParameter("name");
                    String surname = request.getParameter("surname");
                    String email = request.getParameter("email");
                    Integer age = Integer.parseInt(request.getParameter("age"));
                    Integer numb = Integer.parseInt(request.getParameter("number"));
                    Integer series = Integer.parseInt(request.getParameter("series"));
                    if (request.getParameter("id") == null) {
                        person = new Person(id, name, surname);
                        new dao().addUser(person);
                    } else {
                        Integer id1 = Integer.parseInt(request.getParameter("id"));
                        person = new Person(id, name, surname);
                        new dao().updateUser(person);
                    }
                    response.sendRedirect("/");
                    break;
                case "delete":
                    if (request.getParameter("id").length() > 1) {
                        String id = request.getParameter("id");
                        String chunks[] = id.trim().split(" ");
                        for (int i = 0; i < chunks.length; i++) {
                            new dao().deletePerson(Integer.parseInt(chunks[i]));
                        }
                    } else {
                        new dao().deletePerson(Integer.parseInt(request.getParameter("id")));
                    }
                    if (request.getParameter("id") == null){
                        response.sendRedirect("/");
                    }
                    response.sendRedirect("/");
                    break;
            }
        }
        */
    }
}
