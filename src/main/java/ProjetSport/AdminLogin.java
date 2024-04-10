package ProjetSport;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.File;
import java.io.IOException;

/**
 * Servlet implementation class AdminLogin
 */
public class AdminLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminLogin() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@SuppressWarnings("deprecation")
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String username = request.getParameter("user");
		String password = request.getParameter("password");

		int role = 0;
		
		DBDAO dao = new DBDAO();
		
		HttpSession session = request.getSession();
		
		if (username == null || password == null || username.isEmpty() || password.isEmpty()) {
			response.sendError(HttpServletResponse.SC_FORBIDDEN, "Bad Request");
		}
		if (dao.verifierUser(username, password,role)) {
			session.setAttribute("UserName", username);
			
			try {
			    // 你可能需要根据Menu.jar实际的位置来更新这个路径
			    File jarFile = new File("D:\\WorkSpace\\ProjetSport\\src\\main\\java\\ProjetSport\\Menu.jar");

			    ProcessBuilder builder = new ProcessBuilder("java", "-jar", jarFile.getAbsolutePath());
			    builder.directory(jarFile.getParentFile()); 
			    builder.inheritIO(); // 确保Java进程的输出可以在Eclipse控制台中看到
			    Process process = builder.start();

			} catch (IOException e) {
			    System.out.println("Error avec Jar");
			    e.printStackTrace();
			    // 处理异常，例如记录日志或向用户显示错误信息
			}


			
			response.sendRedirect("./index.html");
		} else {
			/* response.sendError(HttpServletResponse.SC_FORBIDDEN, "FORBIDDEN"); */
			 response.sendRedirect("./LoginAdmin.jsp"); 
			 session.setAttribute("LoginFailed", true);
		}
	}

}
