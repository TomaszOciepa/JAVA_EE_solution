package com.isa.usersengine.servlets;

import com.isa.usersengine.dao.UsersRepositoryDao;
import com.isa.usersengine.dao.UsersRepositoryDaoBean;
import com.isa.usersengine.domain.User;
import com.isa.usersengine.freemarker.TemplateProvider;
import com.isa.usersengine.repository.UsersRepository;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@WebServlet(urlPatterns = "/users-list")
public class ShowUsersListServlet extends HttpServlet {

    private static final String TEMPLATE_NAME = "users-list";

    @Inject
    private TemplateProvider templateProvider;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.addHeader("Content-Type", "text/html; charset=utf-8");
        PrintWriter writer = resp.getWriter();

        UsersRepositoryDaoBean usersRepositoryDaoBean = new UsersRepositoryDaoBean();

        List<User> users_list = usersRepositoryDaoBean.getUsersList();

        Template template = templateProvider.getTemplate(getServletContext(), TEMPLATE_NAME);

        Map<String, Object>model = new HashMap<>();
        model.put("user", users_list);

        try {
            template.process(model, writer);
        } catch (TemplateException e) {
            e.printStackTrace();
        }


    }
}
