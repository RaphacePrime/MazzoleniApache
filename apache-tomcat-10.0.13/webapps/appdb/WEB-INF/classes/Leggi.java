import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ResourceBundle;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



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
           
            //caricamento driver
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
        } catch (ClassNotFoundException e) {
            out.println("Errore: Impossibile caricare il Driver Ucanaccess");
        }
        Connection connection=null;
        try {
            //connessione al database
            connection = DriverManager.getConnection(
            "jdbc:ucanaccess://"+ request.getServletContext().getRealPath("/") + "Vuoto.accdb"
            ); 
            out.println("connessione avvenuta con successo");
        } catch (SQLException ex) {
            out.println("Errore: Errore di connessione");
        }
        try{
            //creazione dello Statement
            Statement st = connection.createStatement();
            //esecuzione query
            ResultSet rs=st.executeQuery("select * from numeri;");
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
                out.println("<td>"+rs.getString("nome")+"</td>");
                out.println("<td>"+rs.getInt("numero")+"</td>");
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