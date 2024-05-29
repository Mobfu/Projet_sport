package Cotrolleur;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import Module.EmailSender;
import dao.DBDAO;

/**
 * Servlet implementation class ConfirmPasswordRecoveryServlet
 */
public class ConfirmPasswordRecoveryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	DBDAO dbdao = new DBDAO();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");

        // G¨¦n¨¦rer un code al¨¦atoire
        int randomNumber = dbdao.generateRandomNumber();

        // Stocker l'email et le code dans la session pour v¨¦rification ult¨¦rieure
        request.getSession().setAttribute("email", email);
        request.getSession().setAttribute("code", randomNumber);

        // Envoyer l'e-mail
        EmailSender.sendEmail(email, randomNumber);

        // Rediriger vers la page de confirmation
        response.sendRedirect("RecupMdp.jsp");
    }
}