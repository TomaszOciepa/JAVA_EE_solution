package com.isa.usersengine.servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/welcome-user")
public class WelcomeUserServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String tekst = req.getParameter("name");

        if (tekst == null || tekst.isEmpty()){
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        PrintWriter writer = resp.getWriter();
        String atrr = String.valueOf(req.getAttribute("salary"));

        writer.println("<!DOCTYPE html><html><body>");
        writer.println("Hello <strong>" + tekst + "</strong>!");
        writer.println("</body></html>");

        //resp.getWriter().println("<!DOCTYPE html><html><body>" + tekst + "</body></html>");



        writer.println(atrr);
    }
}

