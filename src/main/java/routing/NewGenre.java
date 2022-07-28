package routing;

import java.io.IOException;
import java.sql.SQLException;

import dataaccess.GenreDAO;
import dataaccess.PlateformeDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Genre;

/**
 * Servlet implementation class NewGenre
 */
public class NewGenre extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public NewGenre() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String message = "";
		String genreId = request.getParameter("genreId");
		Genre genre = null;

		if (genreId == null) {
			genre = new Genre();
		} else {
			try {
				genre = GenreDAO.getGenreById(Integer.parseInt(genreId));
			} catch (Exception e) {
				message = "oops";
			}
		}

		request.setAttribute("message", message);
		// Put genre in the request for the next page		
		request.setAttribute("genre", genre);
		getServletContext().getRequestDispatcher("/WEB-INF/newgenre.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String message = "";
		Genre genre = new Genre();
		genre.setTitre(request.getParameter("titre"));
		genre.setDescription(request.getParameter("description"));

		try {
			if (genre.getGenreId() > 0) {
				// already exists so do an update
				GenreDAO.updateGenre(genre);
				message = "Genre updated";
			} else {
				GenreDAO.insertGenre(genre);
				message = "Genre created";
			}
		} catch (SQLException e) {
			message = "Enter a new title.";
		}

		request.setAttribute("message", message);
		request.setAttribute("genre", genre);
		getServletContext().getRequestDispatcher("/WEB-INF/newgenre.jsp").forward(request, response);
	}
}
