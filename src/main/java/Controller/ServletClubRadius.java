package Controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import dao.ClubDAO;
import Module.Coordonnee;

import com.google.gson.Gson;
import Module.Club;


public class ServletClubRadius extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final Logger LOGGER = Logger.getLogger(ServletClubRadius.class.getName());

    public ServletClubRadius() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        ClubDAO clubdao = new ClubDAO();
        String ourLng = request.getParameter("lonInput");
        String ourLat = request.getParameter("latInput");
        String fedeVal = request.getParameter("fede");
        String rayon = request.getParameter("rayon");

        // Log parameters
        LOGGER.log(Level.INFO, "Received parameters: lonInput={0}, latInput={1}, fede={2}, rayon={3}",
                new Object[]{ourLng, ourLat, fedeVal, rayon});

        // Vérifier si les paramètres ne sont pas null ou vides
        if (ourLng == null || ourLng.isEmpty() || ourLat == null || ourLat.isEmpty() || rayon == null || rayon.isEmpty() || fedeVal == null || fedeVal.isEmpty()) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().write("{\"error\": \"Les paramètres de requête 'lonInput', 'latInput', 'fede' et 'rayon' sont requis.\"}");
            return;
        }

        // Initialiser les variables pour les coordonnées et le rayon
        double latitude;
        double longitude;
        int radius;
        String federation;

        try {
            // Convertir les paramètres en valeurs numériques
            latitude = Double.parseDouble(ourLat);
            longitude = Double.parseDouble(ourLng);
            radius = Integer.parseInt(rayon);
            federation = URLEncoder.encode(fedeVal, StandardCharsets.UTF_8.name());
        } catch (NumberFormatException e) {
            // Gérer les erreurs de conversion
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().write("{\"error\": \"Erreur de conversion des paramètres: " + e.getMessage() + "\"}");
            return;
        }

        // Appeler la méthode DAO pour obtenir les coordonnées
        ArrayList<Club> listClub = clubdao.getClubByFedeRadius(longitude,latitude, radius, fedeVal);

        // Log results
        LOGGER.log(Level.INFO, "Fetched clubs: {0}", listClub);

        // Convertir la liste des coordonnées en JSON
        Gson gson = new Gson();
        String jsonResponse = gson.toJson(listClub);

        out.print(jsonResponse);
        out.flush();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
