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


public class ServletCoorRadius extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public ServletCoorRadius() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        ClubDAO clubdao = new ClubDAO();
        String ourLng = request.getParameter("lonInput");
        String ourLat = request.getParameter("latInput");
        String rayon = request.getParameter("rayon");

        // Vérifier si les paramètres ne sont pas null ou vides
        if (ourLng == null || ourLng.isEmpty() || ourLat == null || ourLat.isEmpty() || rayon == null || rayon.isEmpty()) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().write("{\"error\": \"Les paramètres de requête 'lonInput', 'latInput' et 'rayon' sont requis.\"}");
            return;
        }

        // Initialiser les variables pour les coordonnées et le rayon
        double latitude;
        double longitude;
        int radius;

        try {
            // Convertir les paramètres en valeurs numériques
            latitude = Double.parseDouble(ourLat);
            longitude = Double.parseDouble(ourLng);
            radius = Integer.parseInt(rayon);
        } catch (NumberFormatException e) {
            // Gérer les erreurs de conversion
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().write("{\"error\": \"Erreur de conversion des paramètres: " + e.getMessage() + "\"}");
            return;
        }

        // Appeler la méthode DAO pour obtenir les coordonnées
        ArrayList<Coordonnee> listCoor = clubdao.getCoorsDistance(latitude, longitude, radius);

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
