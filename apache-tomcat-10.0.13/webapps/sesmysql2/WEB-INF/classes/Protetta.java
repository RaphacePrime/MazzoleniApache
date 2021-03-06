
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


public class Protetta extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request,
                      HttpServletResponse response)
        throws IOException, ServletException
    {
        response.setContentType("text/html");
        
        HttpSession sessione = request.getSession(false);
        
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
        
        if (sessione!=null)
        {
            out.println("Benvenuto "+sessione.getAttribute("utente")+".....");
            
            
          //////////////////////////            //////////////////////////          //////////////////////////          //////////////////////////
           try{
            //creazione dello Statement
            Statement st = connection.createStatement();
            //esecuzione query
            //out.println("<h3>select * from voti WHERE username="+sessione.getAttribute("utente")+";</h3>");
            ResultSet rs=st.executeQuery("select username,materia,voto from voti WHERE username LIKE '"+sessione.getAttribute("utente")+"';");

            out.println("<table border='1'>");
            out.println("<tr>");
            out.println("<th>username</th>");
            out.println("<th>materia</th>");
            out.println("<th>voti</th>");
         
            
            //scansione dei risultati
            while(rs.next())
            {
                out.println("<tr>");
                //Contenuto del campo recuperato attraverso l'indice (primo indice 1)
                
                //Contenuto del campo recuperato attraverso il nome del campo
                out.println("<td>"+rs.getString("username")+"</td>");
                out.println("<td>"+rs.getString("materia")+"</td>");
                out.println("<td>"+rs.getFloat("voto")+"</td>");
                out.println("</tr>");
               
            }
                    
            //chiusura connessione
            connection.close();
            out.println("</table>");
        } catch (SQLException ex) {
            out.println("Errore: Errore query");
        }
            
            
            
            
            
            
            
            
            
            
            
    } 
            ////////////////////////////////////////////////////////////////////////////////////
        else{
            out.println("Devi effettuare il login");
        }
        out.println("</body>");
        out.println("</html>");
    }

    @Override
    public void doPost(HttpServletRequest request,
                      HttpServletResponse response)
        throws IOException, ServletException
    {
        doGet(request, response);
    }

}
