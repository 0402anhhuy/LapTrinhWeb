package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.CategoryService;
import service.impl.CategoryServiceImpl;

import java.io.IOException;

@WebServlet("/admin/category/delete")
public class CategoryDeleteController extends HttpServlet {
    CategoryService categoryService = new CategoryServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        categoryService.delete(Integer.parseInt(id));
        resp.sendRedirect(req.getContextPath() + "/admin/category/list");
    }
}
