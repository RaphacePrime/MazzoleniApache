
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


public class Admin extends HttpServlet {

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
            out.println("Benvenuto "+sessione.getAttribute("professore")+".....");
            
                        
          //////////////////////////            //////////////////////////          //////////////////////////          //////////////////////////
           try{
            //creazione dello Statement
            Statement st = connection.createStatement();
            //esecuzione query
            //out.println("<h3>select * from voti WHERE username="+sessione.getAttribute("utente")+";</h3>");
            ResultSet rs=st.executeQuery("select id,username,materia,voto,data from voti;");
            out.println("<center>");
            out.println("<h1>Tabella voti di tutta la classe</h1>");
            out.println("<table border='1'>");
            out.println("<tr>");
            out.println("<th>id</th>");
            out.println("<th>username</th>");
            out.println("<th>materia</th>");
            out.println("<th>voti</th>");
            out.println("<th>data</th>");
            int conta=0;
            
            //scansione dei risultati
            while(rs.next())
            {
                
                out.println("<tr>");
                //Contenuto del campo recuperato attraverso l'indice (primo indice 1)
                
                //Contenuto del campo recuperato attraverso il nome del campo
                out.println("<td>"+rs.getInt("id")+"</td>");
                conta=rs.getInt("id");
                out.println("<td>"+rs.getString("username")+"</td>");
                out.println("<td>"+rs.getString("materia")+"</td>");
                out.println("<td>"+rs.getFloat("voto")+"</td>");
                out.println("<td>"+rs.getObject("data")+"</td>");
                out.println("</tr>");
               
            }
            conta++;
            out.println("</center>");
            out.println("</table>");
                
            ///FORM INSERT
            out.println("<h2>Inserisci un nuovo voto:</h2>");    
            
            
            out.println("<form action='admininsert' method='get'>");    
            out.println("Username: <input type='text' id='floatingInput' name='username' required ><br>");    
            out.println("Materia: <input type='text'  name='materia' required ><br>");    
            out.println("Voto: <input type='text'  name='voto' required ><br>");    
            out.println("Data:<input type='text'  name='data' required >(aaaa-mm-dd)<br>");    
            out.println("Id(selezione automatica):<input type='text'  name='id' value='"+String.valueOf(conta)+"' required placeholder='"+String.valueOf(conta+1)+"' readonly><br>");    
            out.println("<input type='submit'  value='Aggiungi voto' >");    
            out.println("</form>");    
            
            
            
            
            
            out.println("<h2>Elimina un voto(l'ultimo):</h2>");    
            
            
            out.println("<form action='admindelete' method='get' id='idform'>");    
            out.println("<select name='id' form='idform'>");    
            for(int i=1; i<conta; i++)
            {
                out.println("<option value="+String.valueOf(i)+">"+String.valueOf(i)+"</option>");  
            }
            
            out.println("<input type='submit'  value='Elimina voto' >");  
            out.println("</form>"); 
            
            ///FORM INSERT
            out.println("<h2>Aggiorna un voto:</h2>");    
            
            
            out.println("<form action='adminupdate' id='update' method='get'>");    
            out.println("Username: <input type='text' id='floatingInput' name='username' required ><br>");    
            out.println("Materia: <input type='text'  name='materia' required ><br>");    
            out.println("Voto: <input type='text'  name='voto' required ><br>");    
            out.println("Data:<input type='text'  name='data' required >(aaaa-mm-dd)<br>");    
            
            out.println("<select name='id' form='update'>");    
            for(int i=1; i<conta; i++)
            {
                out.println("<option value="+String.valueOf(i)+">"+String.valueOf(i)+"</option>");  
            }
            
            
            
            
            out.println("<input type='submit'  value='Aggiungi voto' >");    
            out.println("</form>");  
            //chiusura connessione
            connection.close();
            
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
