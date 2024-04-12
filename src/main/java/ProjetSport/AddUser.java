package ProjetSport;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 * Servlet implementation class AddUser
 */
public class AddUser extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AddUser() {
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
		boolean res = dao.insertUser(name, password, choix);

		if (res) {
			request.getSession().setAttribute("messageS", "User inscrp succe");
			request.getSession().setAttribute("addSucce", true);
			response.sendRedirect("Login.jsp");

		} else {
			request.getSession().setAttribute("messageF", "User inscrp Non succe");
			request.getSession().setAttribute("addFailed", true);
		}
	}

}
