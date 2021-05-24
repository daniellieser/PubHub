package examples.pubhub.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import examples.pubhub.model.Book;
import examples.pubhub.model.Tag;
import examples.pubhub.utilities.DAOUtilities;

public class TagDAOImpl implements TagDAO {
	Connection connection = null; // Our connection to the database
	PreparedStatement stmt = null;

	@Override
	public List<Tag> getAllTagsForBook(String isbn13) {

		List<Tag> tags = new ArrayList<>();

		try {
			connection = DAOUtilities.getConnection();
			String sql = "SELECT BOOKTAGS.* FROM BOOKTAGS WHERE BOOKTAGS.isbn_13 =?";

			stmt = connection.prepareStatement(sql);

			stmt.setString(1, isbn13);

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				Tag tag = new Tag();

				tag.setName(rs.getString("tag"));

				tags.add(tag);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeResources();
		}

		return tags;
	}

	@Override
	public void addTag(String tag_name, String isbn13) {
		try {
			connection = DAOUtilities.getConnection();
			String sql = "insert into booktags values (?, ?)";
			stmt = connection.prepareStatement(sql);

			stmt.setString(1, isbn13);
			stmt.setString(2, tag_name);

			stmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeResources();
		}

	}

	@Override
	public void removeTag(String tag_name, String isbn13) {

		try {
			connection = DAOUtilities.getConnection();
			String sql = "delete from booktags WHERE isbn_13=? AND tag=?";
			stmt = connection.prepareStatement(sql);

			stmt.setString(1, isbn13);
			stmt.setString(2, tag_name);

			stmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeResources();
		}

	}

	@Override
	public List<Book> getAllBooksForTag(String tag_name) {
		List<Book> books = new ArrayList<>();
		try {
			connection = DAOUtilities.getConnection();
			String sql = "SELECT books.* " + "FROM BOOKS " + "INNER JOIN booktags ON BOOKS.isbn_13=BOOKTAGS.isbn_13 "
					+ "AND booktags.tag = ?"; // Note the ? in
			stmt = connection.prepareStatement(sql);
			stmt.setString(1, tag_name);

			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				Book book = new Book();
				book.setIsbn13(rs.getString("isbn_13"));
				book.setAuthor(rs.getString("author"));
				book.setTitle(rs.getString("title"));
				book.setPublishDate(rs.getDate("publish_date").toLocalDate());
				book.setPrice(rs.getDouble("price"));
				book.setContent(rs.getBytes("content"));

				books.add(book);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeResources();
		}
		return books;
	}

	private void closeResources() {
		try {
			if (stmt != null)
				stmt.close();
		} catch (SQLException e) {
			System.out.println("Could not close statement!");
			e.printStackTrace();
		}

		try {
			if (connection != null)
				connection.close();
		} catch (SQLException e) {
			System.out.println("Could not close connection!");
			e.printStackTrace();
		}
	}
}
