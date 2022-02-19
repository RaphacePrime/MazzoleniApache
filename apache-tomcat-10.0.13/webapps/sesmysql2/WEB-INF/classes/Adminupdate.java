
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ResourceBundle;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


public class Adminupdate extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request,
                      HttpServletResponse response)
        throws IOException, ServletException
    {
        response.setContentType("text/html");
        
        
        
        PrintWriter out = response.getWriter();
        
        
        //CONNESSIONE AL DATABASE
        try {
           //Class.forName("org.mariadb.jdbc.Driver");
            Class.forName("com.mysql.jdbc.Driver");
            out.println("Connecting to a selected database...");
        } catch (ClassNotFoundException e) {
            out.println("Errore: Impossibile caricare il Driver Ucanaccess");
        }
        Connection connection=null;
        try {
              connection = DriverManager
                    .getConnection("jdbc:mysql://localhost/sesmysql?"
                            + "user=root&password=");

            out.println("connessione avvenuta con successo");
        } catch (SQLException ex) {
            out.println("Errore: Errore di connessione");
        }
        out.println("<!DOCTYPE html><html>");
        out.println("<head>");
        out.println("<title>Sessioni</title>");
        out.println("</head>");
        out.println("<body bgcolor=\"white\">");
        
       try{
         //creazione dello Statement
            Statement st = connection.createStatement();
            //esecuzione query
            out.println("UPDATE `voti` SET `voto` = '"+request.getParameter("voto")+"', `username` = '"+request.getParameter("username")+"', `materia` = '"+request.getParameter("materia")+"', `data` = '"+request.getParameter("data")+"', WHERE `voti`.`id` = "+request.getParameter("id")+";");
            st.executeUpdate("UPDATE voti SET voto = '"+request.getParameter("voto")+"', username = '"+request.getParameter("username")+"', materia = '"+request.getParameter("materia")+"', data = '"+request.getParameter("data")+"' WHERE voti.id = "+request.getParameter("id")+";");
            response.sendRedirect("admin");
            
        } catch (SQLException ex) {
            out.println("Errore: Errore query");
        }
            
            
            
            
            
            
            
            
            out.println("</body>");
        out.println("</html>");
            
            
    } 
            ////////////////////////////////////////////////////////////////////////////////////
        
        
    

    @Override
    public void doPost(HttpServletRequest request,
                      HttpServletResponse response)
        throws IOException, ServletException
    {
        doGet(request, response);
    }

}
