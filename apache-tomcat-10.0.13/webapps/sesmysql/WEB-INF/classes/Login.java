
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;


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
        out.println("<body bgcolor=\"white\">");
        if (!request.getParameter("usr").equals("") && 
            request.getParameter("pwd").equals("accedi")){
            out.println("Accesso consentito.....");
            sessione=request.getSession(true);
            out.println(""+sessione);
            sessione.setAttribute("utente",request.getParameter("usr"));
            //redirect;
            response.sendRedirect("protetta");
        }
        else{
            out.println("Accesso non consentito");
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
