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


public class Login extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request,
                      HttpServletResponse response)
        throws IOException, ServletException
    {
        response.setContentType("text/html");
        
        HttpSession sessione; 
        
        PrintWriter out = response.getWriter();
        out.println("<!DOCTYPE html><html>");
        out.println("<head>");
        out.println("<title>Sessioni</title>");
        out.println("</head>");
        out.println("<body bgcolor=\"red\">");
        //CONNESSIONE AL DATABASE
        if (request.getParameter("usr").equals("professore")&& 
                request.getParameter("psw").equals("professore"))
                {
                    out.println("Accesso consentito.....");
                    sessione=request.getSession(true);
                    out.println(""+sessione);
                    sessione.setAttribute("professore",request.getParameter("usr"));
                    //redirect;
                    response.sendRedirect("admin");
                }
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
        out.println("<title>ARGO ScuolaPast</title>");
        out.println("</head>");
        out.println("<body bgcolor=\"white\">");
        
        
        //CONTROLLO CON INPUT UTENTE
        
        try{
            //creazione dello Statement
            Statement st = connection.createStatement();
            //esecuzione query
            ResultSet rs=st.executeQuery("select * from usrpsw;");
            out.println("<table border='1'>");
            out.println("<tr>");
            out.println("<th>USR</th>");
            out.println("<th>PSW</th>");
            
            //scansione dei risultati
            while(rs.next())
            {
                out.println("<tr>");
                //Contenuto del campo recuperato attraverso l'indice (primo indice 1)
                
                //Contenuto del campo recuperato attraverso il nome del campo
                out.println("<td>"+rs.getString("usr")+"</td>");
                out.println("<td>"+rs.getString("psw")+"</td>");
                out.println("</tr>");
                
                
                if (request.getParameter("usr").equals(rs.getString("usr")) && 
                request.getParameter("psw").equals(rs.getString("psw")))
                {
                    out.println("Accesso consentito.....");
                    sessione=request.getSession(true);
                    out.println(""+sessione);
                    sessione.setAttribute("utente",request.getParameter("usr"));
                    //redirect;
                    response.sendRedirect("protetta");
                }
                else
                {
                    out.println("Accesso non consentito");
                }
            }
                    
            //chiusura connessione
            connection.close();
            out.println("</table>");
        } catch (SQLException ex) {
            out.println("Errore: Errore query");
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
