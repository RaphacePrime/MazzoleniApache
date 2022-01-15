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

import java.math.BigDecimal;
import java.sql.*;
import java.time.LocalDateTime;




public class Mazzoleniscrivi extends HttpServlet {

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
        String SQLINSERT="INSERT INTO mazzoleni (nome,marca,prezzo,sconto) VALUES (?,?,?,?)";    
                        
        try{
            Connection connection=null;        
            connection =DriverManager.getConnection(
            "jdbc:ucanaccess://"+ request.getServletContext().getRealPath("/") + "Mazzoleni.accdb"
            ); 
            out.println("connessione avvenuta con successo/////    ");
            PreparedStatement preparedStatement = connection.prepareStatement(SQLINSERT);
            
            preparedStatement.setString(1,request.getParameter("nome"));
            preparedStatement.setString(2,request.getParameter("marca"));
            preparedStatement.setDouble(3,Double.parseDouble(request.getParameter("prezzo")));
            preparedStatement.setLong(4,Long.parseLong(request.getParameter("sconto")));
            preparedStatement.executeUpdate();
            connection.close();   
        }
        catch(Exception e)
        {
            out.println(e);
        }
            
        } 
    }





/*

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



public class Mazzoleniscrivi extends HttpServlet {

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
            "jdbc:ucanaccess://"+ request.getServletContext().getRealPath("/") + "Mazzoleni.accdb"
            ); 
            out.println("connessione avvenuta con successo/////    ");
            
        } catch (SQLException ex) {
            out.println("Errore: Errore di connessione");
        }
        try{         
            //creazione dello Statement
            Statement st = connection.createStatement();
            //esecuzione query
            out.println(   "insert into mazzoleni values ('"+request.getParameter("nome")+"','"+request.getParameter("marca")+"',"+request.getParameter("prezzo")+ ","+request.getParameter("sconto")+");");
            st.executeUpdate(   "insert into mazzoleni (nome,marca,prezzo,sconto) values ('"+request.getParameter("nome")+"','"+request.getParameter("marca")+"',"+request.getParameter("prezzo")+ ","+request.getParameter("sconto")+");"); 
            
            out.println("record inserito correttamente");
            //           st.executeUpdate(   "insert into mazzoleni values ('"+request.getParameter("nome")+"',"+request.getParameter("marca")+"',"+request.getParameter("prezzo")+ "',"+request.getParameter("sconto")+");");  
            //chiusura connessione
            connection.close();
        } catch (SQLException ex) {
            out.println("    //////Errore: Errore di inserimento"+ ex);
        }
    }
}*/
