package routing;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import dataaccess.GenreDAO;
import dataaccess.JeuxDAO;
import dataaccess.PlateformeDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Genre;
import model.Jeux;
import model.Plateforme;

/**
 * Servlet implementation class ListJeux
 */
public class ListJeux extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ListJeux() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Integer plateformeId = null;
		Integer genreId = null;
		String page = "/WEB-INF/showgames.jsp";

		try {
			Optional<String> genre = Optional.ofNullable(request.getParameter("genres"));
			Optional<String> plateformes = Optional.ofNullable(request.getParameter("plateformes"));

			if (genre.isEmpty()) {
				genreId = 0;
			} else if (genre.get().equals("all")) {
				genreId = 0;
			} else if (genre.get().equals("new")) {
				page = "/NewGenre";
				getServletContext().getRequestDispatcher(page).forward(request, response);
				return;
			} else {
				genreId = Integer.parseInt(genre.get());
			}

			if (plateformes.isEmpty()) {
				plateformeId = 0;
			} else if (plateformes.get().equals("all")) {
				plateformeId = 0;
			} else if (plateformes.get().equals("new")) {
				// TODO Add this servlet
				page = "/NewPlateformes";
				getServletContext().getRequestDispatcher(page).forward(request, response);
				return;
			} else {
				plateformeId = Integer.parseInt(plateformes.get());
			}

			List<Jeux> games;
			if (genreId == 0 && plateformeId == 0) {
				games = JeuxDAO.getAllJeux();
			} else {
				if (genreId == 0) {
					// Selection by Plateforme only
					games = JeuxDAO.getJeuxByPlateformeId(plateformeId);
				} else if (plateformeId == 0) {
					// Selection by genre only
					games = JeuxDAO.getJeuxByGenreId(genreId);
				} else {
					// Selection by Genre and plateforme
					games = JeuxDAO.getJeuxByGenreAndPlateforme(genreId, plateformeId);
				}
			}
			// data for the following page
			request.setAttribute("games", games);
			request.setAttribute("selected", genreId);
			request.setAttribute("selectedPlateforme", plateformeId);

			HttpSession session = request.getSession();
			session.setAttribute("genres", GenreDAO.getAllGenres());
			session.setAttribute("plateformes", PlateformeDAO.getAllPlateforme());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		getServletContext().getRequestDispatcher(page).forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
