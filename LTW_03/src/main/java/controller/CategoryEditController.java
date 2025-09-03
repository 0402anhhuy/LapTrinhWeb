package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
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

@WebServlet("/admin/category/edit")
public class CategoryEditController extends HttpServlet {
    private final CategoryService cateService = new CategoryServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String sid = req.getParameter("id");
        if (sid == null) { resp.sendError(400, "Missing id"); return; }
        Category category = cateService.get(Integer.parseInt(sid));
        if (category == null) { resp.sendError(404, "Category not found"); return; }
        req.setAttribute("category", category);
        req.getRequestDispatcher("/views/admin/edit.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");

        DiskFileItemFactory factory = DiskFileItemFactory.builder().get();
        JakartaServletFileUpload upload = new JakartaServletFileUpload(factory);

        // Sẽ gán vào bản ghi có sẵn
        Integer id = null;
        String name = null;
        String newIconRel = null;
        String oldIcon = null;

        try {
            List<FileItem> items = upload.parseRequest(req);

            // Thư mục tĩnh /image/category trong webapp
            String uploadPath = getServletContext().getRealPath("/image/category");
            File uploadDir = new File(uploadPath);
            if (!uploadDir.exists()) uploadDir.mkdirs();

            for (FileItem item : items) {
                if (item.isFormField()) {
                    String field = item.getFieldName();
                    String val = item.getString(StandardCharsets.UTF_8); // v2: dùng Charset

                    switch (field) {
                        case "id":      if (!val.isBlank()) id = Integer.parseInt(val.trim()); break;
                        case "name":    name = val; break;
                        case "oldIcon": oldIcon = val; break;
                    }
                } else if ("icon".equals(item.getFieldName()) && item.getSize() > 0) {
                    String original = item.getName();
                    int slash = Math.max(original.lastIndexOf('/'), original.lastIndexOf('\\'));
                    String base = (slash >= 0) ? original.substring(slash + 1) : original;

                    String safeBase = base.replaceAll("[^A-Za-z0-9._-]", "_");
                    if (safeBase.isBlank()) safeBase = "upload";

                    // tách đuôi
                    int dot = safeBase.lastIndexOf('.');
                    String namePart = (dot >= 0) ? safeBase.substring(0, dot) : safeBase;
                    String extPart  = (dot >= 0) ? safeBase.substring(dot)     : "";

                    String fileName = namePart + extPart;

                    File saved = new File(uploadDir, fileName);
                    item.write(saved.toPath());

                    newIconRel = "image/category/" + fileName;
                }

            }

            if (id == null) { resp.sendError(400, "Missing id"); return; }
            Category db = cateService.get(id);
            if (db == null) { resp.sendError(404, "Category not found"); return; }
            if (name == null || name.isBlank()) { resp.sendError(400, "Name is required"); return; }

            db.setCatename(name);
            if (newIconRel != null) db.setIcon(newIconRel);
            else if (oldIcon != null && !oldIcon.isBlank()) db.setIcon(oldIcon);
            // else: giữ icon hiện tại trong db

            cateService.edit(db); // <-- UPDATE thay vì insert
            resp.sendRedirect(req.getContextPath() + "/admin/category/list");

        } catch (FileUploadException e) {
            throw new ServletException("Upload failed", e);
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }
}
