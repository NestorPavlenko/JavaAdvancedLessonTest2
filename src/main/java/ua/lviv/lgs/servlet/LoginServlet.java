package ua.lviv.lgs.servlet;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("login.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String login = request.getParameter("login");
        String password = request.getParameter("password");

//        UserService userService = UserService.getUserService();
//        User user = userService.getUser(login);

//        if (user == null) {
//            request.getRequestDispatcher("login.jsp").forward(request, response);
//        }
//
//        if (user.getPassword().equals(password)) {
//            request.setAttribute("userEmail", login);
//            request.getRequestDispatcher("cabinet.jsp").forward(request, response);
//        }
        request.getRequestDispatcher("login.jsp").forward(request, response);
    }
}

