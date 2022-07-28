package routing;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
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
			HttpSession session = request.getSession();
			session.setAttribute("jeux", jeux);
			session.setAttribute("plateformes", PlateformeDAO.getJeuxPlateformes(jeux.getJeuxId()));

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
		HttpSession session = request.getSession();
		Jeux jeux = (Jeux) session.getAttribute("jeux");

		jeux.setTitre(request.getParameter("titre"));
		jeux.setDescription(request.getParameter("description"));
		double prix = Double.parseDouble(request.getParameter("prix"));
		jeux.setPrix(prix);

		LocalDate dateSortie = LocalDate.parse(request.getParameter("dateSortie"));
		Date date = Date.valueOf(dateSortie);
		jeux.setDateSortie(date);

		jeux.setPaysOrigine(request.getParameter("paysOrigine"));
		jeux.setConnexion(request.getParameter("connexion"));
		jeux.setMode(request.getParameter("mode"));
		int genreId = Integer.parseInt(request.getParameter("genres"));
		jeux.setGenreId(genreId);

		String message = "";
		JeuxDAO dao = new JeuxDAO();
		try {

			if (jeux.getTitre().isBlank() ||
					jeux.getDescription().isBlank()) {
				message = "You must fill in all fields.";
			} else {

				if (jeux.getJeuxId() == 0) {
					dao.insertJeux(jeux);
					message = "Game added.";
				} else {
					dao.updateJeux(jeux);
					message = "Game updated.";
				}

				String[] plateformes = request.getParameterValues("plateformes");
				dao.clearPlateformes(jeux.getJeuxId());
				if (plateformes != null) {
					dao.UpdatePlateforms(jeux.getJeuxId(), plateformes);
				}
				
				session.setAttribute("plateformes", PlateformeDAO.getJeuxPlateformes(jeux.getJeuxId()));
			}
		} catch (SQLException e) {
			message = "There was an error.";
		}
		session.setAttribute("jeux", jeux);

		request.setAttribute("message", message);
		getServletContext().getRequestDispatcher("/WEB-INF/gamepage.jsp").forward(request, response);
	}
}
