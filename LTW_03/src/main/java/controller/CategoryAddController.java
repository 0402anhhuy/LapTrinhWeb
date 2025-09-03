package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Category;
import org.apache.commons.fileupload2.core.DiskFileItemFactory;
import org.apache.commons.fileupload2.core.FileItem;
import org.apache.commons.fileupload2.core.FileUploadException;
import org.apache.commons.fileupload2.jakarta.JakartaServletFileUpload;
import service.CategoryService;
import service.impl.CategoryServiceImpl;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

@WebServlet("/admin/category/add")
public class CategoryAddController extends HttpServlet {
    CategoryService categoryService = new CategoryServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/views/admin/add.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=UTF-8");

        Category category = new Category();
        
        DiskFileItemFactory factory = DiskFileItemFactory.builder().get();
        JakartaServletFileUpload upload = new JakartaServletFileUpload(factory);

        try {
            List<FileItem> items = upload.parseRequest(req);
            
            String uploadPath = getServletContext().getRealPath("/image/category");
            File uploadDir = new File(uploadPath);
            if (!uploadDir.exists()) uploadDir.mkdirs();

            for (FileItem item : items) {
                if (item.isFormField()) {
                    if ("name".equals(item.getFieldName())) {
                        category.setCatename(item.getString(StandardCharsets.UTF_8));
                    }
                }
                else {
                    if ("icon".equals(item.getFieldName()) && item.getSize() > 0) {
                        String original = item.getName();
                        int slash = Math.max(original.lastIndexOf('/'), original.lastIndexOf('\\'));
                        String base = (slash >= 0) ? original.substring(slash + 1) : original;
                        String safeBase = base.replaceAll("[^A-Za-z0-9._-]", "_");
                        if (safeBase.isBlank()) safeBase = "upload.jpg";

                        File saved = new File(uploadDir, safeBase);
                        if (saved.exists()) {
                            int dot = safeBase.lastIndexOf('.');
                            String namePart = (dot >= 0) ? safeBase.substring(0, dot) : safeBase;
                            String extPart  = (dot >= 0) ? safeBase.substring(dot)     : "";
                            int i = 1;
                            while (saved.exists()) {
                                saved = new File(uploadDir, namePart + "_" + i + extPart);
                                i++;
                            }
                        }

                        item.write(saved.toPath());
                        category.setIcon("image/category/" + saved.getName());
                    }
                }
            }

            if (category.getCatename() == null || category.getCatename().isBlank()) {
                resp.sendError(400, "Name is required");
                return;
            }
            categoryService.insert(category);
            resp.sendRedirect(req.getContextPath() + "/admin/category/list");
        }
        catch (FileUploadException e) {
            throw new ServletException("Upload failed", e);
        }
        catch (Exception e) {
            throw new ServletException(e);
        }
    }
}

