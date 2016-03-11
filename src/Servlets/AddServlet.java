package Servlets;

import Model.Person;

import javax.servlet.*;
import javax.servlet.annotation.ServletSecurity;
import java.sql.*;
import java.util.*;
import java.io.*;

public class AddServlet extends GenericServlet {

    @Override
    public void init(ServletConfig conf){
        try{
            Class.forName("com.mysql.jdbc.Driver");
        }
        catch (ClassNotFoundException ex){
            System.out.println(ex.toString());
        }
    }
    public void service(ServletRequest request, ServletResponse response) throws ServletException, IOException{
        response.setContentType("text/html;charset=utf-8");
        sendSqlForm(request, response);
    }

    private void sendSqlForm(ServletRequest request, ServletResponse response) throws ServletException, IOException{
        PrintWriter pw = response.getWriter();

        pw.println("<HTML>");
        pw.println("<HEAD>");
        pw.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"first.css\">");
        pw.println("<TITLE>Добавить данные</TITLE>");
        pw.println("</HEAD>");
        pw.println("<BODY>");
        pw.println("<H4>SQL Сервлет</H4>");
        pw.println("<FORM action=/sql method=service>");
        pw.println("<TEXTAREA Name=sql cols=90 rows=8 placeholder=\"Введите запрос здесь\" required>");

        String sql = request.getParameter("sql");

        if(sql != null) {pw.print(sql);}

        pw.println("</TEXTAREA>");
        pw.println("<INPUT type=submit value=Отправить>");
        pw.println("<input class=sql value=Очистить type=reset>");

        pw.println("</FORM>");
        pw.println("<BR>");

        if(sql != null) {executeSql(sql.trim(), response, pw);}

        pw.println("</BODY>");
        pw.println("</HTML>");
        pw.close();


    }
    private void executeSql(String sql, ServletResponse response, PrintWriter pw) throws ServletException, IOException{
        try{
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/db_first", "root", "19941124"); //установка соединения
            Statement st = con.createStatement(); //создание объекта для выполнения запросов
            String result = sql.substring(0, 6);

            //select
            //executeQuery
            //insert
            //executeUpdate
            //create
            //update

            if (result.compareToIgnoreCase("select") == 0){
                ResultSet rs = null;

                try{
                    rs = st.executeQuery(sql);
                }
                catch(SQLException ex){
                    response.getWriter().print("База данных недоступна.");
                    return;
                }

                for(int i = 1; i < rs.getMetaData().getColumnCount(); i++) {
                    response.getWriter().print(" - " + rs.getMetaData().getColumnName(i));
                }
                pw.println("<BR>");
                response.getWriter().flush();
                while(rs.next()) {
                    for(int i = 1; i < rs.getMetaData().getColumnCount(); i++) {
                        response.getWriter().print(" - " + rs.getString(i));
                    }
                    pw.println("<BR>");

                }
                rs.close();
            }
            else{
                if (result.compareToIgnoreCase("insert") == 0) {
                    try {
                        st.executeUpdate(sql);
                    }
                    catch (SQLException ex) {
                        response.getWriter().print("База данных недоступна.");
                        return;
                    }
                    response.getWriter().print("Информация успешно добавлена в базу данных.");
                }
                else{
                    if(result.compareToIgnoreCase("create") == 0){
                        try{
                            st.executeUpdate(sql);
                        }
                        catch (SQLException ex){
                            response.getWriter().println("База данных недоступна.");
                            return;
                        }
                        response.getWriter().println("Новая таблица успешно создана.");
                    }
                    else{
                        if (result.compareToIgnoreCase("update") == 0){
                            try{
                                st.executeUpdate(sql);
                            }
                            catch (SQLException ex){
                                response.getWriter().println("База данных недоступна.");
                                return;
                            }
                            response.getWriter().println("Данные успешно обновлены.");
                        }
                        else{
                            response.getWriter().println("Ошибка. Неверный запрос.");
                        }
                    }
                }
            }
            con.close();
            st.close();
        }
        catch (SQLException ex) {
            ex.toString();
        }
    }
}
