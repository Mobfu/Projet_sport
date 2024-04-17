 package dao;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

/**
 * Servlet implementation class ModifNews
 */
public class ModifNews extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ModifNews() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String news = request.getParameter("news");
		String data = request.getParameter("date");
		String montants = request.getParameter("montants");
		
		System.out.println(data);
		DBDAO dao = new DBDAO();
		HttpSession session = request.getSession();
		String username = (String) session.getAttribute("username");
		if(dao.modifNews(username,news,data, montants)) {
			response.sendRedirect("News.jsp");
		}
	}

}