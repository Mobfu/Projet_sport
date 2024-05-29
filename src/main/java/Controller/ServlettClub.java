
package Controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import dao.ClubDAO;
import Module.Club;
import Module.Coordonnee;

import com.google.gson.Gson;



/**
 * Servlet implementation class RestServlet
 */
public class ServlettClub extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServlettClub() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		  // Set content type to JSON
		  // Set content type to JSON
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
    	ClubDAO clubdao= new ClubDAO();
		
		// TODO Auto-generated method stub
		List<Club> cbl = clubdao.getAllClub();
		
        // Create an object to be serialized to JSON
        ExampleResponse exampleResponse = new ExampleResponse("Hello ");

        // Initialize Gson
        Gson gson = new Gson();

        // Convert object to JSON string
        String jsonResponse = gson.toJson(cbl);

        // Write JSON response to the output stream
        response.getWriter().write(jsonResponse);
    }

    // Define a simple class for the response
    private static class ExampleResponse {
        private String message;

        public ExampleResponse(String message) {
            this.message = message;
        }

        public String getMessage() {
            return message;
            }
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
