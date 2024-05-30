package dao;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

/**
 * Servlet implementation class PostComment
 */
public class PostComment extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PostComment() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 String commentText = request.getParameter("comment");
	        int newsId = Integer.parseInt(request.getParameter("newsId"));

	        HttpSession session = request.getSession();
	        String username = (String) session.getAttribute("username");

	        DBDAO dao = new DBDAO();
	        if (dao.addComment(newsId, username, commentText)) {
	            response.sendRedirect("News.jsp");
	        } else {
	            response.sendRedirect("News.jsp?error=Failed to post comment");
	        }
	}

}
