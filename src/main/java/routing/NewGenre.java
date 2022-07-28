package routing;

import java.io.IOException;
import java.sql.SQLException;

import dataaccess.GenreDAO;
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
		Genre genre = new Genre();

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
			GenreDAO.insertGenre(genre);
			message="Genre created";
		} catch (SQLException e) {
			message = "Enter a new title.";
		}

		request.setAttribute("message", message);
		getServletContext().getRequestDispatcher("/WEB-INF/newgenre.jsp").forward(request, response);
	}
}
