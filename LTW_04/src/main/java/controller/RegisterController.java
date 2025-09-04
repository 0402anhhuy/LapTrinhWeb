//package controller;
//
//import jakarta.servlet.ServletException;
//import jakarta.servlet.annotation.WebServlet;
//import jakarta.servlet.http.*;
//import service.UserService;
//import service.impl.UserServiceImpl;
//
//import java.io.IOException;
//
//@WebServlet("/register")
//public class RegisterController extends HttpServlet {
//    public static final String REGISTER = "/views/register.jsp";
//    @Override
//    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        HttpSession session = req.getSession(false);
//        if (session != null && session.getAttribute("username") != null) {
//            resp.sendRedirect(req.getContextPath() + "/admin");
//            return;
//        }
//        Cookie[] cookies = req.getCookies();
//        if (cookies != null) {
//            for (Cookie cookie : cookies) {
//                if (cookie.getName().equals("username")) {
//                    session = req.getSession(true);
//                    session.setAttribute("username", cookie.getValue());
//                    resp.sendRedirect(req.getContextPath() + "/admin");
//                    return;
//                }
//            }
//        }
//        req.getRequestDispatcher("/views/register.jsp").forward(req, resp);
//    }
//    @Override
//    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        resp.setContentType("text/html");
//        resp.setCharacterEncoding("UTF-8");
//        req.setCharacterEncoding("UTF-8");
//        String username = req.getParameter("username");
//        String password = req.getParameter("password");
//        String email = req.getParameter("email");
//        String fullname = req.getParameter("fullname");
//        String phone = req.getParameter("phone");
//        UserService service = new UserServiceImpl();
//        String alertMsg = "";
//        if (service.checkExistEmail(email)) {
//            alertMsg = "Email đã tồn tại!";
//            req.setAttribute("alert", alertMsg);
//            req.getRequestDispatcher("/views/register.jsp").forward(req, resp);
//            return;
//        }
//        if (service.checkExistUsername(username)) {
//            alertMsg = "Tài khoản đã tồn tại!";
//            req.setAttribute("alert", alertMsg);
//            req.getRequestDispatcher("/views/register.jsp").forward(req, resp);
//            return;
//        }
//        if (service.checkExistPhone(phone)) {
//            alertMsg="Số điện thoại đã được đăng ký";
//            req.setAttribute("alert", alertMsg);
//            req.getRequestDispatcher("/views/register.jsp").forward(req, resp);
//            return;
//        }
//        boolean isSuccess = service.register(email, password, username, fullname, phone);
//        System.out.println(isSuccess);
//        if (isSuccess) {
//            req.setAttribute("alert", alertMsg);
//            resp.sendRedirect(req.getContextPath() + "/login");
//        } else {
//            alertMsg = "System error!";
//            req.setAttribute("alert", alertMsg);
//            req.getRequestDispatcher("/views/register.jsp").forward(req, resp);
//        }
//    }
//}
//
