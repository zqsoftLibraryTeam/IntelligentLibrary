package com.intelligentLibrary.dev.domain;

/**
 * 推荐信息，即每个人的推荐
 * @author 12146
 *
 */
public class Recommandinfo {
	private Integer id;
	private User user;
	private Book book;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Book getBook() {
		return book;
	}
	public void setBook(Book book) {
		this.book = book;
	}
	public Recommandinfo() {
		super();
	
	}
	public Recommandinfo(Integer id, User user, Book book) {
		super();
		this.id = id;
		this.user = user;
		this.book = book;
	}
}
