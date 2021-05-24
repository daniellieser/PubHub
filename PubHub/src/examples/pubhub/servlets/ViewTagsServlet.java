package examples.pubhub.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import examples.pubhub.dao.TagDAO;
import examples.pubhub.model.Tag;
import examples.pubhub.utilities.DAOUtilities;

@WebServlet("/viewTags")
public class ViewTagsServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.getRequestDispatcher("viewTags.jsp").forward(request, response);

	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String isbn13 = request.getParameter("isbn13");
		System.out.println("isbn13 is: " + isbn13);
		TagDAO tagdao = DAOUtilities.getTagDAO();
		// TagDAO tagdaoimpl = new TagDAOImpl();
		List<Tag> tagList = tagdao.getAllTagsForBook(isbn13);

		// Populate the list into a variable that will be stored in the session
		request.getSession().setAttribute("tags", tagList);
		System.out.println(tagList);
		request.getRequestDispatcher("viewTags.jsp").forward(request, response);
		System.out.println(tagList);
	}

}
