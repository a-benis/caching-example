package com.example.caching_example.book.repo;

import com.example.caching_example.book.domain.Book;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.QueryHint;

/**
 * The interface Book repository.
 */
@Repository
public interface BookRepository extends CrudRepository<Book, Long> {

	/**
	 * Find all iterable.
	 *
	 * @return the iterable
	 */
	@Override
	@QueryHints(@QueryHint(name = org.hibernate.annotations.QueryHints.CACHEABLE, value = "true"))
	Iterable<Book> findAll();

	/**
	 * Find book by book type iterable.
	 *
	 * @param bookType the book type
	 * @return the iterable
	 */
	@QueryHints(@QueryHint(name = org.hibernate.annotations.QueryHints.CACHEABLE, value = "true"))
	Iterable<Book> findBookByBookType(String bookType);
}
