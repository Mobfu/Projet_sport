package ProjetSport;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.time.LocalDateTime;

/**
 * Servlet implementation class UserLogin
 */
public class UserLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserLogin() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String username = request.getParameter("user");
		String password = request.getParameter("password");
		String role = request.getParameter("role");

		int choix = 0;

		switch (role) {
		case "Elu":
			choix = 1;
			break;
		case "Acteur":
			choix = 2;
			break;
		case "User":
			choix = 3;
			break;
		}

		DBDAO dao = new DBDAO();
		
		HttpSession session = request.getSession();
		LocalDateTime now = LocalDateTime.now();
		System.out.println(now);
		
		if (username == null || password == null || username.isEmpty() || password.isEmpty()) {
			response.sendError(HttpServletResponse.SC_FORBIDDEN, "Bad Request");
		}
		if (dao.checkUser(username, password,choix)) {
			session.setAttribute("UserName", username);
			dao.saveTempLogin(now, choix);
			response.sendRedirect("./index.jsp");
		} else {
			/* response.sendError(HttpServletResponse.SC_FORBIDDEN, "FORBIDDEN"); */
			 response.sendRedirect("./Login.jsp"); 
			 session.setAttribute("LoginFailed", true);
		}
	}

}
