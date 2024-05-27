 package dao;

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
		String email = request.getParameter("email");
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
		}

		DBDAO dao = new DBDAO();
		
		HttpSession session = request.getSession();
		LocalDateTime now = LocalDateTime.now();
		System.out.println(now);
		
		if (email == null || password == null || email.isEmpty() || password.isEmpty()) {
			response.sendError(HttpServletResponse.SC_FORBIDDEN, "Bad Request");
		}
		if (dao.checkUser(email, password,choix)) {
			int id = dao.getIdByEmail(email);
			String username = dao.getNameByEmail(email);
			session.setAttribute("LogFlag", true);
			session.setAttribute("id", id);
			session.setAttribute("username", username);
			session.setAttribute("role", choix);
			dao.saveTempLogin(now, choix);
			if(choix==1) {
				response.sendRedirect("./MembresElu.jsp");
			}
			if(choix==2) {
				response.sendRedirect("./index.jsp");
			}
			
		} else {
			/* response.sendError(HttpServletResponse.SC_FORBIDDEN, "FORBIDDEN"); */
			 response.sendRedirect("./Login.jsp"); 
			 session.setAttribute("LoginFailed", true);
		}
	}

}
