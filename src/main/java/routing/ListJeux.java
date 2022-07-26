package routing;

import java.io.IOException;
import java.sql.SQLException;
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
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String page = "/showgames.jsp";

		try {
			List<Genre> genres = GenreDAO.getAllGenres();
			List<Jeux> games = JeuxDAO.getAllJeux();
			request.setAttribute("genres", genres);
			request.setAttribute("games", games);
		} catch (SQLException e) {
			 page = "/error.jsp";
		}		
		getServletContext().getRequestDispatcher(page).forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
