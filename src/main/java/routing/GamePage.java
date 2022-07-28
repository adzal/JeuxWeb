package routing;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;

import dataaccess.GenreDAO;
import dataaccess.JeuxDAO;
import dataaccess.PlateformeDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
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

			Jeux jeux = null;
			if (jeuxId == null) {
				jeux = new Jeux();
			} else {
				jeux = JeuxDAO.getJeuxbyJeuxId(Integer.parseInt(jeuxId));
			}
			request.setAttribute("genres", GenreDAO.getAllGenres());

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
		String message = "";

		HttpSession session = request.getSession();
		Jeux jeux = (Jeux) session.getAttribute("jeux");

		jeux.setTitre(request.getParameter("titre"));
		jeux.setDescription(request.getParameter("description"));

		jeux.setPaysOrigine(request.getParameter("paysOrigine"));
		jeux.setConnexion(request.getParameter("connexion"));
		jeux.setMode(request.getParameter("mode"));
		int genreId = Integer.parseInt(request.getParameter("genres"));
		jeux.setGenreId(genreId);

		try {
			double prix = Double.parseDouble(request.getParameter("prix"));
			jeux.setPrix(prix);
		} catch (NumberFormatException e) {
			message = "Please enter a valid price";
		}

		try {
			LocalDate dateSortie = LocalDate.parse(request.getParameter("dateSortie"));
			Date date = Date.valueOf(dateSortie);
			jeux.setDateSortie(date);
		} catch (java.time.format.DateTimeParseException e) {
			message = "Please enter a date in yyyy-mm-dd format";
		}

		JeuxDAO dao = new JeuxDAO();

		if (jeux.getTitre().isBlank() ||
				jeux.getDescription().isBlank()) {
			message = "You must fill in all fields.";
		} else {
			try {
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
			} catch (SQLException e) {
				message = "There was an error.";
			}
		}
		session.setAttribute("jeux", jeux);

		request.setAttribute("message", message);
		getServletContext().getRequestDispatcher("/WEB-INF/gamepage.jsp").forward(request, response);
	}
}
