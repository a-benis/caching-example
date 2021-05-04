package com.example.caching_example.book.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * The type Book.
 */
@Entity
@Table(name = "book")
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE, region = "Book")
public class Book implements Serializable {

	/**
	 * The constant serialVersionUID.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * The Id.
	 */
	@Id
	@GeneratedValue
	private Long id;

	/**
	 * The Name.
	 */
	@Column(name = "name", nullable = false)
	private String name;

	/**
	 * The Book type.
	 */
	@Column(name = "book_type")
	private String bookType;

	/**
	 * Gets id.
	 *
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * Sets id.
	 *
	 * @param id the id
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * Gets name.
	 *
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets name.
	 *
	 * @param name the name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Gets book type.
	 *
	 * @return the book type
	 */
	public String getBookType() {
		return bookType;
	}

	/**
	 * Sets book type.
	 *
	 * @param bookType the book type
	 */
	public void setBookType(String bookType) {
		this.bookType = bookType;
	}

	/**
	 * To string string.
	 *
	 * @return the string
	 */
	@Override
	public String toString() {
		return "Book{" +
				"id=" + id +
				", name='" + name + '\'' +
				", bookType='" + bookType + '\'' +
				'}';
	}
}
