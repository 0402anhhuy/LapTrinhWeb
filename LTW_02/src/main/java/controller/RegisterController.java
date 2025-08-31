package controller;

import constant.Path;
import service.UserService;
import service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/register")
public class RegisterController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        HttpSession session = req.getSession(false);
        if (session != null && session.getAttribute("account") != null) {
            resp.sendRedirect(req.getContextPath() + "/waiting");
            return;
        }
        req.getRequestDispatcher(Path.VIEW_REGISTER).forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=UTF-8");

        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String email    = req.getParameter("email");
        String fullname = req.getParameter("fullname");
        String phone    = req.getParameter("phone");

        UserService userService = new UserServiceImpl();
        String alertMsg;

        if (userService.checkExistEmail(email)) {
            alertMsg = "Email đã tồn tại";
            req.setAttribute("alertMsg", alertMsg);
            req.getRequestDispatcher(Path.REGISTER).forward(req, resp);
            return;
        }

        if (userService.checkExistUsername(username)) {
            alertMsg = "Tên tài khoản đã tồn tại";
            req.setAttribute("alertMsg", alertMsg);
            req.getRequestDispatcher(Path.REGISTER).forward(req, resp);
            return;
        }

        if (userService.checkExistPhone(phone)) {
            alertMsg = "Số điện thoại đã tồn tại";
            req.setAttribute("alertMsg", alertMsg);
            req.getRequestDispatcher(Path.REGISTER).forward(req, resp);
            return;
        }

        boolean isSuccess = userService.register(username, fullname, email, phone, password);
        if (isSuccess) {
            resp.sendRedirect(req.getContextPath() + Path.LOGIN);
        }
        else {
            alertMsg = "Đăng ký thất bại";
            req.setAttribute("alertMsg", alertMsg);
            req.getRequestDispatcher(Path.REGISTER).forward(req, resp);
        }
    }
}
