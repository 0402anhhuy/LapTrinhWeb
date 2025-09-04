//package controller;
//
//import jakarta.servlet.ServletException;
//import jakarta.servlet.annotation.WebServlet;
//import jakarta.servlet.http.HttpServlet;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import service.UserService;
//import service.impl.UserServiceImpl;
//
//import java.io.IOException;
//
//@WebServlet("/resetpassword")
//public class ResetPassword extends HttpServlet {
//    @Override
//    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        String email = req.getParameter("email");
//        req.setAttribute("email", email);
//        req.getRequestDispatcher("/views/resetpass.jsp").forward(req, resp);
//    }
//
//    @Override
//    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        resp.setContentType("text/html");
//        req.setCharacterEncoding("UTF-8");
//        resp.setCharacterEncoding("UTF-8");
//        String email = req.getParameter("email");
//        String newPassword = req.getParameter("newPassword");
//        String confirmPassword = req.getParameter("confirmPassword");
//        if (newPassword.equals(confirmPassword)) {
//            UserService userService = new UserServiceImpl();
//            userService.updatePassword(email,newPassword);
//            req.getRequestDispatcher("/views/login.jsp").forward(req,resp);
//        }
//        else {
//            req.setAttribute("alert", "Mật khẩu không trùng với nhau");
//            req.getRequestDispatcher("/views/resetpass.jsp").forward(req, resp);
//        }
//    }
//}
