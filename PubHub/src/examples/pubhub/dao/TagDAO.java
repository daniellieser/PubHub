package examples.pubhub.dao;

import java.util.List;

import examples.pubhub.model.Book;
import examples.pubhub.model.Tag;

public interface TagDAO {

	List<Tag> getAllTagsForBook(String isbn13);

	void addTag(String tag_name, String isbn13);

	void removeTag(String tag_name, String isbn13);

	List<Book> getAllBooksForTag(String tag_name);
}
