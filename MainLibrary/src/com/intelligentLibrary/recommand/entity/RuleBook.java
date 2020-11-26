package com.intelligentLibrary.recommand.entity;

import com.intelligentLibrary.dev.domain.Book;

public class RuleBook {
	private Integer id;
	private Book currentBook;
	private Book targetBook;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Book getCurrentBook() {
		return currentBook;
	}
	public void setCurrentBook(Book currentBook) {
		this.currentBook = currentBook;
	}
	public Book getTargetBook() {
		return targetBook;
	}
	public RuleBook() {
		super();
	
	}
	public RuleBook(Integer id, Book currentBook, Book targetBook) {
		super();
		this.id = id;
		this.currentBook = currentBook;
		this.targetBook = targetBook;
	}
	public void setTargetBook(Book targetBook) {
		this.targetBook = targetBook;
	}
	
}
