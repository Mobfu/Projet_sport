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

        // V¨¦rifier que l'e-mail est stock¨¦ dans la session
        if (email == null) {
            response.getWriter().println("Erreur: Session expir¨¦e ou email non valide.");
            return;
        }

        // Appeler la m¨¦thode de mise ¨¤ jour du mot de passe dans le DAO
        DBDAO dbdao = new DBDAO();
        boolean isUpdated = dbdao.updatePassword(email, newPassword);

        if (isUpdated) {
            response.getWriter().println("Mot de passe r¨¦initialis¨¦ avec succ¨¨s.");
        } else {
            response.getWriter().println("Erreur lors de la r¨¦initialisation du mot de passe. Veuillez r¨¦essayer.");
        }
    }
}
