package dao;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.time.LocalDateTime;

/**
 * Servlet implementation class UserLogin
 */
@WebServlet("/UserLogin")
public class UserLogin extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public UserLogin() {
        super();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String role = request.getParameter("role");

        int choix = 0;

        switch (role) {
            case "Elu":
                choix = 1;
                break;
            case "Acteur":
                choix = 2;
                break;
            default:
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid role");
                return;
        }

        DBDAO dao = new DBDAO();

        HttpSession session = request.getSession();
        LocalDateTime now = LocalDateTime.now();
        System.out.println(now);

        if (email == null || password == null || email.isEmpty() || password.isEmpty()) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Email or password cannot be empty");
            return;
        }

        if (dao.checkUser(email, password, choix)) {
            int id = dao.getIdByEmail(email);
            String username = dao.getNameByEmail(email);
            session.setAttribute("LogFlag", true);
            session.setAttribute("id", id);
            session.setAttribute("username", username);
            session.setAttribute("role", choix);
            dao.saveTempLogin(now, choix);

            if (choix == 1) {
                response.sendRedirect("./MembresElu.jsp");
            } else if (choix == 2) {
                response.sendRedirect("./index.jsp");
            }
        } else {
            session.setAttribute("LoginFailed", true);
            response.sendRedirect("./Login.jsp");
        }
    }
}
