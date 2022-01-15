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
           //Class.forName("org.mariadb.jdbc.Driver");
            Class.forName("com.mysql.jdbc.Driver");
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
            st.executeUpdate("insert into campi (stringa,intero) values ('"+request.getParameter("nom")+"',"+request.getParameter("num")+");");  
            out.println("record inserito correttamente");
            //chiusura connessione
            connection.close();
        } catch (SQLException ex) {
            out.println("Errore: Errore di inserimento"+ex);
        }
    }
}
