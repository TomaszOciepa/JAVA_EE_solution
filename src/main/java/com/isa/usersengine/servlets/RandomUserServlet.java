package com.isa.usersengine.servlets;

import com.isa.usersengine.cdi.RandomUserCDIApplicationDaoBean;
import com.isa.usersengine.cdi.RandomUserCDIRequestDaoBean;
import com.isa.usersengine.cdi.RandomUserCDISessionDaoBean;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/random-user")
public class RandomUserServlet extends HttpServlet {

    @Inject
    RandomUserCDIApplicationDaoBean applicationDaoBean;
    @Inject
    RandomUserCDISessionDaoBean sessionDaoBean;
    @Inject
    RandomUserCDIRequestDaoBean requestDaoBean;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        PrintWriter writer = resp.getWriter();

        writer.println(applicationDaoBean.getRandomUser().getName());
        writer.println(sessionDaoBean.getRandomUser().getName());
        writer.println(requestDaoBean.getRandomUser().getName());

    }
}