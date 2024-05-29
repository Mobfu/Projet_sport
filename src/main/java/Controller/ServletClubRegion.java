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


import com.google.gson.Gson;
import Module.Club;


public class ServletClubRegion extends HttpServlet {
    private static final long serialVersionUID = 1L;
    

    public ServletClubRegion() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        ClubDAO clubdao = new ClubDAO();
        String fedeVal = request.getParameter("fede");
        String regiVal = request.getParameter("regi");



        // Vérifier si les paramètres ne sont pas null ou vides
        if (fedeVal == null || fedeVal.isEmpty() || regiVal == null || regiVal.isEmpty()) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().write("{\"error\": \"Les paramètres de requête'fede' et 'regi' sont requis.\"}");
            return;
        }

        // Initialiser les variables pour les coordonnées et le rayon
       
        

       

        // Appeler la méthode DAO pour obtenir les coordonnées
        ArrayList<Club> listClub = clubdao.getListByRegion(fedeVal,regiVal);

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
