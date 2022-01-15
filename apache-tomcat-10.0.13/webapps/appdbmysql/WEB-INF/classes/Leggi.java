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



public class Leggi extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request,
                      HttpServletResponse response)
        throws IOException, ServletException
    {
        //setta il tipo di contenuto nell'header http
        response.setContentType("text/html");
        //recupera lo stream di output 
        PrintWriter out = response.getWriter();
        out.println("<html>");
        out.println("<body>");
        try {
           //Class.forName("org.mariadb.jdbc.Driver");
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("Connecting to a selected database...");
        } catch (ClassNotFoundException e) {
            out.println("Errore: Impossibile caricare il Driver Ucanaccess");
        }
        Connection connection=null;
        try {
              connection = DriverManager
                    .getConnection("jdbc:mysql://localhost/provacampi?"
                            + "user=provacampi&password=provacampi");

            out.println("connessione avvenuta con successo");
        } catch (SQLException ex) {
            out.println("Errore: Errore di connessione");
        }
        try{
            //creazione dello Statement
            Statement st = connection.createStatement();
            //esecuzione query
            ResultSet rs=st.executeQuery("select * from campi;");
            out.println("<table border='1'>");
            out.println("<tr>");
            out.println("<th>ID</th>");
            out.println("<th>NOME</th>");
            out.println("<th>NUMERO</th>");
            //scansione dei risultati
            while(rs.next()){
                out.println("<tr>");
                //Contenuto del campo recuperato attraverso l'indice (primo indice 1)
                out.println("<td>"+rs.getInt(1)+"</td>");
                //Contenuto del campo recuperato attraverso il nome del campo
                out.println("<td>"+rs.getString("stringa")+"</td>");
                out.println("<td>"+rs.getInt("intero")+"</td>");
                out.println("</tr>");
            }
            out.println("</table>");         
            //chiusura connessione
            connection.close();
            
        } catch (SQLException ex) {
            out.println("Errore: Errore query");
        }
        
        out.println("</body>");
        out.println("</html>");
    }
}