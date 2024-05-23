package ProjetSport;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Servlet implementation class SuprimNews
 */
public class SuprimNews extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SuprimNews() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String id = request.getParameter("id");
		int newid = Integer.parseInt(id);

		DBDAO dao = new DBDAO();
		if (dao.SuprimerNews(newid)) {
			request.getSession().setAttribute("supsucce", true);
			response.sendRedirect("News.jsp");
		} else {
			request.getSession().setAttribute("supfailed", true);
			response.sendRedirect("News.jsp");
		}
	}

}
