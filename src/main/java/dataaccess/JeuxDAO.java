package dataaccess;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.Jeux;

public class JeuxDAO {
	public static List<Jeux> getAllJeux() throws SQLException {
		List<Jeux> jeuxList = new ArrayList<>();

		String q = "SELECT Jeux_Id,Jeux_Titre,Jeux_Description,Jeux_Prix,Jeux_DateSortie,"
				+ "Jeux_PaysOrigine,Jeux_Connexion,Jeux_Mode,jeux.Genre_Id, "
				+ "genre.genre_description as genre_desc "
				+ "FROM jeux "
				+ "inner join genre on genre.genre_id = jeux.genre_id;";

		// try with resources PreparedStatement implements AutoCloseable
		// ConnectionFactory c'est une usine qui donne une connection
		// PreparedStatement plus securisé que statement normal (pas de SQL injection)
		// pour envoyé au BDD la requête.
		try (Connection connection = ConnectionFactory.getInstance().getConnection();
				PreparedStatement p = connection.prepareStatement(q)) {
			
			// execute the query, and get a java resultset
			try (ResultSet rs = p.executeQuery()) {

				// iterate through the java resultset
				while (rs.next()) {
					Jeux jeux = new Jeux();

					jeux.setJeuxId(rs.getInt("jeux_Id"));
					jeux.setTitre(rs.getString("jeux_titre"));
					jeux.setDescription(rs.getString("jeux_description"));
					jeux.setPrix(rs.getDouble("jeux_prix"));
					jeux.setDateSortie(rs.getDate("jeux_datesortie"));
					jeux.setPaysOrigine(rs.getString("jeux_paysorigine"));
					jeux.setConnexion(rs.getString("jeux_connexion"));
					jeux.setMode(rs.getString("jeux_mode"));
					jeux.setGenreId(rs.getInt("genre_id"));
					jeux.setGenreDescription(rs.getString("genre_desc"));
					
					jeuxList.add(jeux);
				}
			}
		}

		return jeuxList;
	}

	public static List<Jeux> getJeuxByGenreId(int genreId) throws SQLException {
		List<Jeux> jeuxList = new ArrayList<>();

		String q = "SELECT Jeux_Id,Jeux_Titre,Jeux_Description,Jeux_Prix,Jeux_DateSortie,"
				+ "Jeux_PaysOrigine,Jeux_Connexion,Jeux_Mode,Genre_Id,jeux.Genre_Id, "
				+ "genre.genre_description as genre_desc "
				+ "FROM jeux "
				+ "inner join genre on genre.genre_id = jeux.genre_id"
				+ "where Genre_Id = ?";

		// try with resources PreparedStatement implements AutoCloseable
		// ConnectionFactory c'est une usine qui donne une connection
		// PreparedStatement plus securisé que statement normal (pas de SQL injection)
		// pour envoyé au BDD la requête.
		try (Connection connection = ConnectionFactory.getInstance().getConnection();
				PreparedStatement p = connection.prepareStatement(q)) {
			p.setInt(1, genreId);

			// execute the query, and get a java resultset
			try (ResultSet rs = p.executeQuery()) {

				// iterate through the java resultset
				while (rs.next()) {
					Jeux jeux = new Jeux();

					jeux.setJeuxId(rs.getInt("jeux_Id"));
					jeux.setTitre(rs.getString("jeux_titre"));
					jeux.setDescription(rs.getString("jeux_description"));
					jeux.setPrix(rs.getDouble("jeux_prix"));
					jeux.setDateSortie(rs.getDate("jeux_datesortie"));
					jeux.setPaysOrigine(rs.getString("jeux_paysorigine"));
					jeux.setConnexion(rs.getString("jeux_connexion"));
					jeux.setMode(rs.getString("jeux_mode"));
					jeux.setGenreId(rs.getInt("genre_id"));
					jeux.setGenreDescription(rs.getString("genre_desc"));
					
					jeuxList.add(jeux);
				}
			}
		}

		return jeuxList;
	}

	public void updateTitreById(int jeuxId, String newTitre) throws SQLException {
		String q = "update jeux set jeux_titre = ? where jeux_id = ?";

		try (Connection connection = ConnectionFactory.getInstance().getConnection();
				PreparedStatement p = connection.prepareStatement(q)) {
			p.setString(1, newTitre);
			p.setInt(2, jeuxId);

			p.execute();
		}
	}

	public void insertJeux(Jeux jeux) throws SQLException {
		String q = "insert jeux values(null,?,?,?,?,?,?,?,?)";

		try (Connection connection = ConnectionFactory.getInstance().getConnection();
				PreparedStatement p = connection.prepareStatement(q, Statement.RETURN_GENERATED_KEYS)) {
			p.setString(1, jeux.getTitre());
			p.setString(2, jeux.getDescription());
			p.setDouble(3, jeux.getPrix());
			p.setDate(4, jeux.getDateSortie());
			p.setString(5, jeux.getPaysOrigine());
			p.setString(6, jeux.getConnexion());
			p.setString(7, jeux.getJeuxMode());
			p.setInt(8, jeux.getGenreId());
			int affectedRows = p.executeUpdate();

			if (affectedRows == 0) {
				throw new SQLException("Creating Jeux failed, no rows affected.");
			}

			try (ResultSet generatedKeys = p.getGeneratedKeys()) {
				if (generatedKeys.next()) {
					jeux.setJeuxId(generatedKeys.getInt(1));
				} else {
					throw new SQLException("Creating Jeux failed, no ID obtained.");
				}
			}
		}
	}

	public void deleteJeuxById(int jeuxId) throws SQLException {
		String q = "Delete from jeux where jeux_id = ?";

		try (Connection connection = ConnectionFactory.getInstance().getConnection();
				PreparedStatement p = connection.prepareStatement(q)) {
			p.setInt(1, jeuxId);
			p.execute();
		}
	}
}
