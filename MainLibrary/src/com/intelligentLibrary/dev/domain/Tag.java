package com.intelligentLibrary.dev.domain;

/**
 * IlTag entity. @author MyEclipse Persistence Tools
 */

public class Tag implements java.io.Serializable {

	// Fields

	private Integer id;
	private Book book;
	private Keyword keyword;

	// Constructors

	/** default constructor */
	public Tag() {
	}



	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}



	public Book getBook() {
		return book;
	}



	public void setBook(Book book) {
		this.book = book;
	}



	public Keyword getKeyword() {
		return keyword;
	}



	public void setKeyword(Keyword keyword) {
		this.keyword = keyword;
	}



	public Tag(Integer id, Book book, Keyword keyword) {
		super();
		this.id = id;
		this.book = book;
		this.keyword = keyword;
	}


}