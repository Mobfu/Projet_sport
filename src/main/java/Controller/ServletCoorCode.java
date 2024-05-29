package Controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import dao.ClubDAO;
import Module.Coordonnee;

import com.google.gson.Gson;


public class ServletCoorCode extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public ServletCoorCode() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        ClubDAO clubdao = new ClubDAO();
        
        String fedeVal = request.getParameter("fede");
        String codeVal = request.getParameter("codep");

        // Vérifier si les paramètres ne sont pas null ou vides
        if (fedeVal == null || fedeVal.isEmpty() || codeVal == null ||	codeVal.isEmpty()) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().write("{\"error\": \"Les paramètres de requête 'lonInput', 'latInput' et 'rayon' sont requis.\"}");
            return;
        }

        // Initialiser les variables pour les coordonnées et le rayon
       
        // Appeler la méthode DAO pour obtenir les coordonnées
        ArrayList<Coordonnee> listCoor = clubdao.getCoorsByCode(fedeVal,codeVal);

        // Convertir la liste des coordonnées en JSON
        Gson gson = new Gson();
        String jsonResponse = gson.toJson(listCoor);
        
        
        // Write the JSON response
        out.print(jsonResponse);
        out.flush();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
