

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.Enumeration;
import java.util.ResourceBundle;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;


public class Protetta extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request,
                      HttpServletResponse response)
        throws IOException, ServletException
    {
        response.setContentType("text/html");
        
        HttpSession sessione = request.getSession(false);
        
        PrintWriter out = response.getWriter();
        
        out.println("<!DOCTYPE html><html>");
        out.println("<head>");
        out.println("<title>Sessioni</title>");
        out.println("</head>");
        out.println("<body bgcolor=\"white\">");
        
        if (sessione!=null)
            out.println("Benvenuto "+sessione.getAttribute("utente")+".....");
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
