package examples.pubhub.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import examples.pubhub.dao.TagDAO;
import examples.pubhub.model.Book;
import examples.pubhub.utilities.DAOUtilities;

@WebServlet("/TagPublishingHome")
public class TagPublishingServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.getRequestDispatcher("tagPublishingHome.jsp").forward(request, response);

	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String tag_name = request.getParameter("tag_name");
		System.out.println("tag_name is: " + tag_name);
		TagDAO tagdao = DAOUtilities.getTagDAO();
		// TagDAO tagdaoimpl = new TagDAOImpl();
		List<Book> bookList = tagdao.getAllBooksForTag(tag_name);

		// Populate the list into a variable that will be stored in the session
		request.getSession().setAttribute("books", bookList);
		System.out.println(bookList);
		request.getRequestDispatcher("tagPublishingHome.jsp").forward(request, response);
		System.out.println(bookList);
	}

}
