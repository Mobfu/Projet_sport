package dao;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

/**
 * Servlet implementation class UploadSportPrefer
 */
public class UploadSportPrefer extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UploadSportPrefer() {
        super();
        // TODO Auto-generated constructor stub
    }


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Object iduser = request.getSession().getAttribute("id");
		int id = Integer.parseInt(iduser.toString());
		
		String basketball = request.getParameter("basketball");
		String football = request.getParameter("football");
		String tennis = request.getParameter("tennis");
		String others = request.getParameter("others");
		
		StringBuilder sportPreferBuilder = new StringBuilder();
	    if (basketball != null) {
	        sportPreferBuilder.append("Basketball,");
	    }
	    if (football != null) {
	        sportPreferBuilder.append("Football,");
	    }
	    if (tennis != null) {
	        sportPreferBuilder.append("Tennis,");
	    }
	    if (others != null) {
	        sportPreferBuilder.append("Others,");
	    }

	    if (sportPreferBuilder.length() > 0) {
	        sportPreferBuilder.deleteCharAt(sportPreferBuilder.length() - 1);
	    }

	    String sports = sportPreferBuilder.toString();
	    HttpSession session = request.getSession();
	    session.setAttribute("sports", sports);
	    
	    DBDAO dao = new DBDAO();
	    if (dao.UploadSportPrefer(id, sports)) {
	        System.out.println("Updated sports preferences successfully!");
	        response.sendRedirect("Profil.jsp");
	    } else {
	        System.out.println("Failed to update sports preferences!");
	    }
	}

}
