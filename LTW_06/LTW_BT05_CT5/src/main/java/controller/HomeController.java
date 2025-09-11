package controller;

import enity.UserProfile; // nếu đúng là entity thì sửa lại chính tả (entity)
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload2.core.DiskFileItemFactory;
import org.apache.commons.fileupload2.core.FileItem;
import org.apache.commons.fileupload2.core.FileUploadException;
import org.apache.commons.fileupload2.jakarta.JakartaServletFileUpload;

import service.UserProfileService;
import service.impl.UserProfileServiceImpl;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

@WebServlet("/add")
public class HomeController extends HttpServlet {

    private final UserProfileService userProfileService = new UserProfileServiceImpl();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html; charset=UTF-8");

        // Kiểm tra multipart
        if (!JakartaServletFileUpload.isMultipartContent(req)) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Form phải là multipart/form-data");
            return;
        }

        UserProfile userProfile = new UserProfile();

        DiskFileItemFactory factory = DiskFileItemFactory.builder().get();
        JakartaServletFileUpload upload = new JakartaServletFileUpload(factory);

        try {
            List<FileItem> items = upload.parseRequest(req);

            // Thư mục upload: /image/categories
            String folder = "/image/categories";
            String uploadPath = getServletContext().getRealPath(folder);
            File uploadDir = new File(uploadPath);
            if (!uploadDir.exists()) uploadDir.mkdirs();

            for (FileItem item : items) {
                if (item.isFormField()) {
                    // --- Xử lý input text ---
                    switch (item.getFieldName()) {
                        case "name":
                            userProfile.setFullName(item.getString(StandardCharsets.UTF_8));
                            break;
                        case "phone":
                            userProfile.setPhone(item.getString(StandardCharsets.UTF_8));
                            break;
                    }
                } else {
                    // --- Xử lý file upload ---
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

                        String publicUrl = req.getContextPath() + folder + "/" + saved.getName();
                        userProfile.setImage(publicUrl);
                    }
                }
            }

            // --- Validate ---
            if (userProfile.getFullName() == null || userProfile.getFullName().isBlank()) {
                resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Chưa nhập tên danh mục");
                return;
            }
            if (userProfile.getPhone() == null || !userProfile.getPhone().matches("^(0|\\+84)([0-9]{9,10})$")) {
                resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Số điện thoại không hợp lệ");
                return;
            }

            // --- Lưu DB ---
            userProfileService.createUserProfile(userProfile);

        } catch (FileUploadException e) {
            throw new ServletException("Tải file thất bại", e);
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }
}
