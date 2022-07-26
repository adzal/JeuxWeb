package routing;

import java.io.IOException;
import java.util.List;

import dataaccess.GenreDAO;
import dataaccess.JeuxDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Genre;
import model.Jeux;

/**
 * Servlet implementation class GamePage
 */
public class GamePage extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public GamePage() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			String jeuxId = request.getParameter("jeuxId");
			List<Genre> genres = GenreDAO.getAllGenres();
			request.setAttribute("genres", genres);
			Jeux jeux = JeuxDAO.getJeuxbyJeuxId(Integer.parseInt(jeuxId));
			request.setAttribute("jeux", jeux);
			getServletContext().getRequestDispatcher("/WEB-INF/gamepage.jsp").forward(request, response);
		} catch (Exception e) {
			getServletContext().getRequestDispatcher("/error.jsp").forward(request, response);
		}
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
