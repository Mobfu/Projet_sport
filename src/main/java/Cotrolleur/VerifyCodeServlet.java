package Cotrolleur;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/VerifyCodeServlet1")
public class VerifyCodeServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String code = request.getParameter("code");

        int sessionCode = (int) request.getSession().getAttribute("code");

        if (code.equals(String.valueOf(sessionCode))) {
            response.sendRedirect("ResetPassword.jsp");
        } else {
            response.getWriter().println("Le code de r¨¦cup¨¦ration est incorrect. Veuillez r¨¦essayer.");
        }
    }
}
