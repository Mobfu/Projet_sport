 package dao;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 * Servlet implementation class AddUser
 */
public class AdUser extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AdUser() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String name = request.getParameter("name");
		String password = request.getParameter("password");
		String email = request.getParameter("email");
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
		boolean res = dao.insertUser(name,email, password, choix);

		if (res) {
			request.getSession().setAttribute("addSucce", true);
			response.sendRedirect("Login.jsp");

		} else {
			request.getSession().setAttribute("addFailed", true);
			response.sendRedirect("addUser.jsp");
		}
	}

}
