package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.UserService;
import service.impl.UserServiceImpl;

import java.io.IOException;

@WebServlet("/forgotpassword")
public class ForgotPassControlller extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/views/forgot.jsp").forward(req,resp);
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        String email = req.getParameter("email");
        UserService userService = new UserServiceImpl();
        boolean emailExits = userService.checkExistEmail(email);
        if(!emailExits){
            req.setAttribute("alert", "Email không tồn tại");
            req.getRequestDispatcher("/views/forgot.jsp").forward(req,resp);
        }else {
            resp.sendRedirect(req.getContextPath() + "/resetpassword?email=" + email);
        }

    }
}
