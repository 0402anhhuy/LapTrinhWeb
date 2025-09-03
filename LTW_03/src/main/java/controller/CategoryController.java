package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Category;
import service.CategoryService;
import service.impl.CategoryServiceImpl;

import java.io.IOException;
import java.util.List;

@WebServlet("/admin/category/list")
public class CategoryController extends HttpServlet {
    CategoryService categoryService = new CategoryServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Category> categoryList = categoryService.getAll();
        req.setAttribute("categoryList", categoryList);
        req.getRequestDispatcher("/views/admin/list.jsp").forward(req, resp);
    }
}
