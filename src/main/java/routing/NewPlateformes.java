package routing;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Plateforme;

import java.io.IOException;
import java.sql.SQLException;

import dataaccess.PlateformeDAO;

/**
 * Servlet implementation class NewPlateformes
 */
public class NewPlateformes extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NewPlateformes() {
        super();
        // TODO Auto-generated constructor stub
    }
    /**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//Create an empty plateforme so that the page is empty
		Plateforme plateforme = new Plateforme();

		request.setAttribute("plateforme", plateforme);
		getServletContext().getRequestDispatcher("/WEB-INF/newplateforme.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String message = "";
		Plateforme Plateforme = new Plateforme();
		Plateforme.setNom(request.getParameter("nom"));
		Plateforme.setDescription(request.getParameter("description"));

		try {
			PlateformeDAO.insertPlateforme(Plateforme);
			message = "plateforme created";
		} catch (SQLException e) {
			message = "Enter a new plateforme nom.";
		}

		request.setAttribute("message", message);
		getServletContext().getRequestDispatcher("/WEB-INF/newplateforme.jsp").forward(request, response);
	}
}
