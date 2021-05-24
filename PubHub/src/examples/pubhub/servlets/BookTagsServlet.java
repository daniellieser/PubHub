package examples.pubhub.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import examples.pubhub.dao.TagDAO;
import examples.pubhub.dao.TagDAOImpl;

public class BookTagsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher("bookTags.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String isbn13 = req.getParameter("isbn");
		String tag_name = req.getParameter("tag_name");
		String action = req.getParameter("action");
		System.out.println("action is " + action);
		TagDAO dao = new TagDAOImpl();
		if ("add".equals(action)) {
			System.out.println("calling add tag");
			dao.addTag(tag_name, isbn13);
		} else if ("remove".equals(action)) {
			System.out.println("calling remove tag");
			dao.removeTag(tag_name, isbn13);
		}
//		BookDAO database = DAOUtilities.getBookDAO();
//		Book tempBook = database.getBookByISBN(isbn13);

		// ASSERT: book with isbn already exists

		req.getSession().setAttribute("message",
				"ISBN is " + isbn13 + " and tag name is " + tag_name + " and action is " + action);
		req.getSession().setAttribute("messageClass", "alert-danger");
		req.getRequestDispatcher("bookTags.jsp").forward(req, resp);

	}
}
