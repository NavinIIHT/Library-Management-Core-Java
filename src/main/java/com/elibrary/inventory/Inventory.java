package com.elibrary.inventory;

import java.util.ArrayList;
import java.util.List;

import com.elibrary.exception.BookNotFoundException;
import com.elibrary.exception.ISBNAlreadyExistsException;
import com.elibrary.models.Book;

public class Inventory {
	public List<Book> books = new ArrayList<>();

	// Add a book to the inventory
	// Throw ISBNAlreadyExistsException, if same isbn number book is added
	public boolean addBook(Book book) throws ISBNAlreadyExistsException {
		String isbn = book.getIsbn();
		for (Book existingBook : books) {
			if (existingBook.getIsbn().equalsIgnoreCase(isbn)) {
				throw new ISBNAlreadyExistsException("ISBN already exists.");
			}
		}
		books.add(book);
		return true;
	}

	// Search for a book by name
	public List<Book> searchByName(String name) {
		List<Book> result = new ArrayList<>();
		for (Book book : books) {
			if (book.getTitle().equalsIgnoreCase(name)) {
				result.add(book);
			}
		}
		return result;
	}

	// Search for books by author
	public List<Book> searchByAuthor(String author) {
		List<Book> result = new ArrayList<>();
		for (Book book : books) {
			if (book.getAuthor().equalsIgnoreCase(author)) {
				result.add(book);
			}
		}
		return result;
	}

	// Search for books by publisher
	public List<Book> searchByPublisher(String publisher) {
		List<Book> result = new ArrayList<>();
		for (Book book : books) {
			if (book.getPublisher().equalsIgnoreCase(publisher)) {
				result.add(book);
			}
		}
		return result;
	}

	// Check book availability by ISBN
	public boolean checkAvailability(String isbn) {
		for (Book book : books) {
			if (book.getIsbn().equalsIgnoreCase(isbn)) {
				return book.isAvailable();
			}
		}
		return false;
	}

	// Update book details by ISBN
	// Throw BookNotFoundException if passed isbn is not found
	public boolean updateBook(String isbn, Book updatedBook) throws BookNotFoundException {
		boolean found = false;
		for (int i = 0; i < books.size(); i++) {
			if (books.get(i).getIsbn().equalsIgnoreCase(isbn)) {
				books.set(i, updatedBook);
				found = true;
				break;
			}
		}
		if (!found) {
			throw new BookNotFoundException("Book not found for ISBN: " + isbn);
		} else {
			return true;
		}
	}

	// Delete a book by ISBN
	// Throw BookNotFoundException if passed isbn is not found
	public boolean deleteBook(String isbn) throws BookNotFoundException {
		boolean found = false;
		for (int i = 0; i < books.size(); i++) {
			if (books.get(i).getIsbn().equalsIgnoreCase(isbn)) {
				books.remove(i);
				found = true;
				break;
			}
		}
		if (!found) {
			throw new BookNotFoundException("Book not found for ISBN: " + isbn);
		}
		return true;
	}
}
