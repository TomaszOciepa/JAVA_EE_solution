package com.isa.usersengine.servlets;

import com.isa.usersengine.cdi.FileUploadProcessorDao;
import com.isa.usersengine.cdi.FileUploadProcessorDaoBean;
import com.isa.usersengine.dao.UsersRepositoryDao;
import com.isa.usersengine.dao.UsersRepositoryDaoBean;
import com.isa.usersengine.domain.User;
import com.isa.usersengine.exceptions.UserImageNotFound;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;

@MultipartConfig
@WebServlet("/add-user")
public class AddUserServlet extends HttpServlet {

    @Inject
    FileUploadProcessorDaoBean fileUploadProcessorDaoBean;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String idString = req.getParameter("id");
        String name = req.getParameter("name");
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        String ageString = req.getParameter("age");
        Part filePart = req.getPart("image");
        File file = null;

        Integer id = Integer.parseInt(idString);
        Integer age = Integer.parseInt(ageString);

        User newUser = new User();
        UsersRepositoryDao addNewUser = new UsersRepositoryDaoBean();

        resp.getWriter().println("<!DOCTYPE html><html><body>");

        resp.getWriter().println("Users before adding: " + addNewUser.getUsersList().size() + "<br/><br/>");

        try {
            file = fileUploadProcessorDaoBean.uploadImageFile(filePart);
        } catch (UserImageNotFound userImageNotFound) {
            userImageNotFound.printStackTrace();
        }

        newUser.setId(id);
        newUser.setName(name);
        newUser.setLogin(login);
        newUser.setPassword(password);
        newUser.setAge(age);
        newUser.setImageURL("/images/" + file.getName());

        addNewUser.addUser(newUser);

        resp.getWriter().println("Users after adding: " + addNewUser.getUsersList().size() + "<br/><br/>");

        resp.getWriter().println("<a href=\"/images/JBusters_logo.png\">Pobierz obraz</a>");

        resp.getWriter().println("</body></html>");

    }
}
