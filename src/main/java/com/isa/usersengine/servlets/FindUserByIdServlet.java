package com.isa.usersengine.servlets;

import com.isa.usersengine.dao.UsersRepositoryDao;
import com.isa.usersengine.dao.UsersRepositoryDaoBean;
import com.isa.usersengine.domain.User;
import com.isa.usersengine.repository.UsersRepository;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/find-user-by-id")
public class FindUserByIdServlet extends HttpServlet {

    @EJB
    private UsersRepositoryDao repo;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String idString = req.getParameter("id");
        Integer id = Integer.parseInt(req.getParameter("id"));

        if (idString == null || idString.isEmpty()){
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        //UsersRepositoryDao repo = new UsersRepositoryDaoBean();

        User user = repo.getUserById(id);

        PrintWriter writer = resp.getWriter();

        if (user != null) {
            writer.println("<!DOCTYPE html><html><body>");
            writer.println("Found user: <strong>" + user.getName() + "</strong>");
            writer.println("</body></html>");
        } else {
            writer.println("User not found");
            resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
        }
    }
}
