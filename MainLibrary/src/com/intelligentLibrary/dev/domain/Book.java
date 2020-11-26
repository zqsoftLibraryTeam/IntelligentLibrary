package com.intelligentLibrary.dev.domain;

import java.util.HashSet;
import java.util.Set;

/**
 * IlBook entity. @author MyEclipse Persistence Tools
 */

public class Book implements java.io.Serializable {

	// Fields

	private Integer bookid;			//书本id
	private BookShell ilBookshell;	//书架
	private BookSort ilBooksort;	//书本类型
	private String bookname;		//书名
	private String isbn;			//isbn
	private String author;			//作者
	private String press;			//出版社
	private String picture;			//图片
	private String status;			//书本状态
	private Integer booknum;		//书本数量
	private Set<BorrowInfo> ilBorrowinfos = new HashSet<BorrowInfo>();	//书本借阅信息
	private Set<Comment> comments=new HashSet<Comment>();				//书本评论
	private Set<Tag> tags=new HashSet<Tag>();							//书 的标签
	private Set<Recommandinfo> recommands=new HashSet<Recommandinfo>();	//推荐信息
	private Set<OrderBook> orderBooks=new HashSet<OrderBook>();			//预定图书

	public Set<OrderBook> getOrderBooks() {
		return orderBooks;
	}

	public void setOrderBooks(Set<OrderBook> orderBooks) {
		this.orderBooks = orderBooks;
	}

	public Set<Recommandinfo> getRecommands() {
		return recommands;
	}

	public void setRecommands(Set<Recommandinfo> recommands) {
		this.recommands = recommands;
	}

	/** default constructor */
	public Book() {
	}


	// Property accessors


	public Book(Integer bookid, BookShell ilBookshell, BookSort ilBooksort, String bookname, String isbn, String author,
			String press, String picture, String status, Integer booknum, Set<BorrowInfo> ilBorrowinfos,
			Set<Comment> comments, Set<Tag> tags, Set<Recommandinfo> recommands, Set<OrderBook> orderBooks) {
		super();
		this.bookid = bookid;
		this.ilBookshell = ilBookshell;
		this.ilBooksort = ilBooksort;
		this.bookname = bookname;
		this.isbn = isbn;
		this.author = author;
		this.press = press;
		this.picture = picture;
		this.status = status;
		this.booknum = booknum;
		this.ilBorrowinfos = ilBorrowinfos;
		this.comments = comments;
		this.tags = tags;
		this.recommands = recommands;
		this.orderBooks = orderBooks;
	}

	public Integer getBookid() {
		return this.bookid;
	}

	public void setBookid(Integer bookid) {
		this.bookid = bookid;
	}

	public BookShell getIlBookshell() {
		return this.ilBookshell;
	}

	public void setIlBookshell(BookShell ilBookshell) {
		this.ilBookshell = ilBookshell;
	}

	public BookSort getIlBooksort() {
		return this.ilBooksort;
	}

	public void setIlBooksort(BookSort ilBooksort) {
		this.ilBooksort = ilBooksort;
	}

	public String getBookname() {
		return this.bookname;
	}

	public void setBookname(String bookname) {
		this.bookname = bookname;
	}

	public String getIsbn() {
		return this.isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getAuthor() {
		return this.author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getPress() {
		return this.press;
	}

	public void setPress(String press) {
		this.press = press;
	}

	public String getPicture() {
		return this.picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Integer getBooknum() {
		return this.booknum;
	}

	public void setBooknum(Integer booknum) {
		this.booknum = booknum;
	}

	public Set<?> getIlBorrowinfos() {
		return this.ilBorrowinfos;
	}

	public Set<Comment> getComments() {
		return comments;
	}

	public void setComments(Set<Comment> comments) {
		this.comments = comments;
	}

	public Set<Tag> getTags() {
		return tags;
	}

	public void setTags(Set<Tag> tags) {
		this.tags = tags;
	}

	public void setIlBorrowinfos(Set<BorrowInfo> ilBorrowinfos) {
		this.ilBorrowinfos = ilBorrowinfos;
	}



}