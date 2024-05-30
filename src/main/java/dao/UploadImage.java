package dao;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;

import org.apache.tomcat.util.http.fileupload.FileItem;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;
import org.apache.tomcat.util.http.fileupload.servlet.ServletRequestContext;

/**
 * Servlet implementation class UploadImage
 */
public class UploadImage extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UploadImage() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Process only if it's multipart/content data
    	if (ServletFileUpload.isMultipartContent(request)) {
            try {
                DiskFileItemFactory factory = new DiskFileItemFactory();
                ServletFileUpload upload = new ServletFileUpload(factory);

                // ʹ�� ServletRequestContext ��װԭʼ�� HttpServletRequest
                ServletRequestContext requestContext = new ServletRequestContext(request);

                // ��������
                List<FileItem> items = upload.parseRequest(requestContext);
                String fileName = null;

                for (FileItem item : items) {
                    if (!item.isFormField()) {
                        fileName = new java.io.File(item.getName()).getName();
                        break; // ����ֻ�ϴ�һ���ļ�
                    }
                }

                if (fileName != null && !fileName.isEmpty()) {
                    HttpSession session = request.getSession();
                    Object iduser = session.getAttribute("id");
                    int id = Integer.parseInt(iduser.toString());
                    DBDAO dao = new DBDAO();
                    
                    if (dao.UploadImage(id, fileName)) {
                        System.out.println("Updated image successfully: " + fileName);
                        session.setAttribute("imagename", fileName);
                        response.sendRedirect("Profil.jsp");
                    } else {
                        System.out.println("Failed to update image!");
                        response.sendRedirect("Profil.jsp");
                        
                    }
                } else {
                    System.out.println("No file uploaded.");
                    response.sendRedirect("Profil.jsp");
                    
                }
            } catch (Exception ex) {
            	response.sendRedirect("Profil.jsp");
            }
        } else {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Request content type is not multipart/form-data.");
        }
    }
}
