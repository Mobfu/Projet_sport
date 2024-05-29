package Controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

/**
 * Servlet implementation class SaveData
 */
@WebServlet("/SaveData13")
public class SaveData2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SaveData2() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String fedeVal = (String) request.getParameter("fede");
		String regiVal = (String) request.getParameter("regi");
		String codeVal = (request.getParameter("codep") != null) ? request.getParameter("codep") : "";
		String commuVal = (String) request.getParameter("commu");
		String searchVal = (String) request.getParameter("searchType");
		HttpSession session = request.getSession();
		
		session.setAttribute("fede",fedeVal);
		session.setAttribute("regi",regiVal);
		session.setAttribute("codep",codeVal);
		session.setAttribute("searchType",searchVal);
		session.setAttribute("commu",commuVal);
		
		
		response.sendRedirect("map.jsp");
	}

}
