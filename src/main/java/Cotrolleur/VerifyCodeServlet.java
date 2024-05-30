package Cotrolleur;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/VerifyCodeServlet1")
public class VerifyCodeServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	HttpSession session = request.getSession();
        String sessionCode = (String) session.getAttribute("sessionCode");
        String code = request.getParameter("code");

        if (code != null && code.equals(String.valueOf(sessionCode))) {
            response.sendRedirect("ResetPassword.jsp");
        } else {
            request.setAttribute("errorMessage", "Code incorrect");
            request.getRequestDispatcher("RecupMdp.jsp").forward(request, response);
        }
    }
}
