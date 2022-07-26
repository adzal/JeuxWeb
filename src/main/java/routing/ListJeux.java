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
			Optional<String> genre = Optional.ofNullable(request.getParameter("genres"));
			if (genre.isPresent()) {
				if (genre.get().equals("new")) {
					page = "NewGenre";
				} else {
					List<Jeux> games;
					if (genre.get().equals("all")) {
						games = JeuxDAO.getAllJeux();
					} else {
						Integer genreId = Integer.parseInt(genre.get());
						games = JeuxDAO.getJeuxByGenreId(genreId);
					}
					request.setAttribute("games", games);

					List<Genre> genres = GenreDAO.getAllGenres();
					request.setAttribute("genres", genres);
					
					request.setAttribute("selected", genre.get());
				}
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
