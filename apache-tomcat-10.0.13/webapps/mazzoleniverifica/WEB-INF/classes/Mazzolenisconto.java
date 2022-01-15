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



public class Mazzolenisconto extends HttpServlet {

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
            "jdbc:ucanaccess://"+ request.getServletContext().getRealPath("/") + "Mazzoleni.accdb"
            ); 
            out.println("connessione avvenuta con successo");
        } catch (SQLException ex) {
            out.println("Errore: Errore di connessione");
        }
        try{
            //creazione dello Statement
            Statement st = connection.createStatement();
            //esecuzione query
            ResultSet rs=st.executeQuery("select * from mazzoleni;");
            out.println("<table border='1'>");
            out.println("<tr>");
            out.println("<th>NOME</th>");
            out.println("<th>MARCA</th>");
            out.println("<th>PREZZO SCONTATO</th>");
            
            //scansione dei risultati
            while(rs.next()){
                out.println("<tr>");
                //Contenuto del campo recuperato attraverso l'indice (primo indice 1)
                
                //Contenuto del campo recuperato attraverso il nome del campo
                int a=rs.getInt("prezzo");
                int sconto=rs.getInt("sconto");
                int appoggio=a/100;
                int scontoapplicato=appoggio*sconto;
                int output=a-scontoapplicato;
                
                
                out.println("<td>"+rs.getString("nome")+"</td>");
                out.println("<td>"+rs.getString("marca")+"</td>");
                out.println("<td>"+output+" €"+"</td>");
                
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