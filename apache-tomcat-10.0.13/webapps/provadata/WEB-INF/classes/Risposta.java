/*
* Licensed to the Apache Software Foundation (ASF) under one or more
* contributor license agreements.  See the NOTICE file distributed with
* this work for additional information regarding copyright ownership.
* The ASF licenses this file to You under the Apache License, Version 2.0
* (the "License"); you may not use this file except in compliance with
* the License.  You may obtain a copy of the License at
*
*     http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
*/

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ResourceBundle;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


public class Risposta extends HttpServlet {

    

    @Override
    public void doGet(HttpServletRequest request,
                      HttpServletResponse response)
        throws IOException, ServletException
    {
       

        response.setContentType("text/html");
        

        PrintWriter out = response.getWriter();
        out.println("<!DOCTYPE html><html>");
        out.println("<head>");
        out.println("<meta charset=\"UTF-8\" />");

        
        out.println("<title>" + "Ciao bro" + "</title>");
        out.println("</head>");
        out.println("<body bgcolor=\"white\">");

       

        out.println("<h3>" + "SIUUUUUUUUUUUM" + "</h3>");
        String parametro0 = request.getParameter("num");
        //System.out.println(parametro0 + "NUM");
        int num0 = Integer.parseInt(parametro0);
        int ris = num0*num0;
        //System.out.println(ris+ "OUTPUT");
        
        
        out.println("<h3>" + "Numero al quadrato : "+ ris + "</h3>");
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
