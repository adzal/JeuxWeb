package routing;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import dataaccess.GenreDAO;
import dataaccess.JeuxDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Genre;
import model.Jeux;

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
		String page = "/showgames.jsp";
		try {
			String genre = request.getParameter("genres");
			if (genre == null) {
				genre = "all";
			}
			if (genre.equals("new")) {
				// TODO Add this servlet
				page = "NewGenre";
			} else {
				List<Jeux> games;
				if (genre.equals("all")) {
					games = JeuxDAO.getAllJeux();
				} else {
					Integer genreId = Integer.parseInt(genre);
					games = JeuxDAO.getJeuxByGenreId(genreId);

					request.setAttribute("selected", genre);
				}
				request.setAttribute("games", games);

				List<Genre> genres = GenreDAO.getAllGenres();
				request.setAttribute("genres", genres);
			}
		} catch (Exception e) {
			page = "/error.jsp";
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
