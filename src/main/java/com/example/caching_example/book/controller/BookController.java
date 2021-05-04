package com.example.caching_example.book.controller;

import com.example.caching_example.book.domain.Book;
import com.example.caching_example.book.dto.CachingDataDto;
import com.example.caching_example.book.repo.BookRepository;
import com.example.caching_example.book.service.ICachingExampleService;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The type Book controller.
 */
@RestController
@RequestMapping("/api/v1")
public class BookController {

	/**
	 * The Book repository.
	 */
	@Autowired
	private BookRepository bookRepository;

	/**
	 * The Caching example service.
	 */
	@Autowired
	private ICachingExampleService cachingExampleService;

	/**
	 * Gets all books.
	 *
	 * @return the all books
	 */
	@GetMapping("/books")
	public List<Book> getAllBooks() {
		return (List<Book>) bookRepository.findAll();
	}

	/**
	 * Gets book by id.
	 *
	 * @param bookId the book id
	 * @return the book by id
	 * @throws Exception the exception
	 */
	@GetMapping("/books/{id}")
	public ResponseEntity<Book> getBookById(@PathVariable(value = "id") Long bookId) throws Exception {
		Book book = bookRepository.findById(bookId)
				.orElseThrow(() -> new Exception("Book " + bookId + " not found"));
		return ResponseEntity.ok().body(book);
	}

	/**
	 * Gets book by book type.
	 *
	 * @param bookType the book type
	 * @return the book by book type
	 */
	@GetMapping("/books-by-type/{type}")
	public List<Book> getBookByBookType(@PathVariable(value = "type") String bookType) {
		return (List<Book>) bookRepository.findBookByBookType(bookType);
	}

	/**
	 * Create book book.
	 *
	 * @param book the book
	 * @return the book
	 */
	@PostMapping("/books")
	public Book createBook(@Valid @RequestBody Book book) {
		return bookRepository.save(book);
	}

	/**
	 * Update book response entity.
	 *
	 * @param bookId      the book id
	 * @param bookDetails the book details
	 * @return the response entity
	 * @throws Exception the exception
	 */
	@PutMapping("/books/{id}")
	public ResponseEntity<Book> updateBook(@PathVariable(value = "id") Long bookId, @Valid @RequestBody Book bookDetails) throws Exception {
		Book book = bookRepository.findById(bookId)
				.orElseThrow(() -> new Exception("Book " + bookId + " not found"));

		book.setName(bookDetails.getName());
		book.setBookType(bookDetails.getBookType());

		final Book updatedBook = bookRepository.save(book);
		return ResponseEntity.ok(updatedBook);
	}

	/**
	 * Delete book map.
	 *
	 * @param bookId the book id
	 * @return the map
	 * @throws Exception the exception
	 */
	@DeleteMapping("/book/{id}")
	public Map<String, Boolean> deleteBook(@PathVariable(value = "id") Long bookId) throws Exception {
		Book book = bookRepository.findById(bookId)
				.orElseThrow(() -> new Exception("Book " + bookId + " not found"));

		bookRepository.delete(book);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}

	/**
	 * Store object string.
	 *
	 * @param key            the key
	 * @param cachingDataDto the caching data dto
	 * @return the string
	 * @throws IOException the io exception
	 */
	@PostMapping("/store/caching-example")
	public String storeObject(@Valid @RequestHeader String key, @Valid @RequestBody CachingDataDto cachingDataDto) throws IOException {
		Object obj = cachingExampleService.storeObjectInCache(key, cachingDataDto);
		Gson gson = new Gson();
		return gson.toJson(obj);
	}

	/**
	 * Read object string.
	 *
	 * @param key   the key
	 * @param value the value
	 * @return the string
	 * @throws IOException the io exception
	 */
	@GetMapping("/read/caching-example")
	public String readObject(@Valid @RequestHeader String key, @Valid @RequestHeader String value) throws IOException {
		Object obj = cachingExampleService.readObjectFromCache(key, value);
		Gson gson = new Gson();
		return gson.toJson(obj);
	}

	/**
	 * Update object string.
	 *
	 * @param key   the key
	 * @param value the value
	 * @return the string
	 * @throws IOException the io exception
	 */
	@PutMapping("/update/caching-example")
	public String updateObject(@Valid @RequestHeader String key, @Valid @RequestHeader String value) throws IOException {
		Object obj = cachingExampleService.updateInCache(key, value);
		Gson gson = new Gson();
		return gson.toJson(obj);
	}
}
