package controller;

import model.User;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(urlPatterns = "/waiting")
public class WaitingController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {

        HttpSession session = req.getSession(false);
        if (session != null && session.getAttribute("account") != null) {
            User user = (User) session.getAttribute("account");

            if (user.getRoleid() == 1) {
                resp.sendRedirect(req.getContextPath() + "/home");
            }
            else {
                resp.sendRedirect(req.getContextPath() + "/home");
            }
        }
        else {
            resp.sendRedirect(req.getContextPath() + "/login");
        }
    }
}
