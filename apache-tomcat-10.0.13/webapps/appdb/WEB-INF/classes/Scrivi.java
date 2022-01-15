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



public class Scrivi extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request,
                      HttpServletResponse response)
        throws IOException, ServletException
    {
        //setta il tipo di contenuto nell'header http
        response.setContentType("text/html");
        //recupera lo stream di output 
        PrintWriter out = response.getWriter();
        //caricamento driver
        try {
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
        } catch (ClassNotFoundException e) {
            out.println("Errore: Impossibile caricare il Driver Ucanaccess");
        }
        //connessione al database
        Connection connection=null;
        try {
            connection =DriverManager.getConnection(
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
            st.executeUpdate("insert into numeri values (null, '"+request.getParameter("nom")+"',"+request.getParameter("num")+");");  
            out.println("record inserito correttamente");
            //chiusura connessione
            connection.close();
        } catch (SQLException ex) {
            out.println("Errore: Errore di inserimento");
        }
    }
}
