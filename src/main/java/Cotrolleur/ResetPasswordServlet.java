package Cotrolleur;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import dao.DBDAO;

@WebServlet("/ResetPasswordServlet1")
public class ResetPasswordServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String newPassword = request.getParameter("newPassword");
        String email = (String) request.getSession().getAttribute("email");

        // Vérifier que l'e-mail est stocké dans la session
        if (email == null) {
            response.getWriter().println("Erreur: Session expirée ou email non valide.");
            return;
        }

        // Appeler la méthode de mise à jour du mot de passe dans le DAO
        DBDAO dbdao = new DBDAO();
        boolean isUpdated = dbdao.updatePassword(email, newPassword);

        if (isUpdated) {
        	 response.sendRedirect("Login.jsp");;
        } else {
            response.getWriter().println("Erreur lors de la réinitialisation du mot de passe. Veuillez réessayer.");
            response.sendRedirect("ResetPassword.jsp");
        }
    }
}
