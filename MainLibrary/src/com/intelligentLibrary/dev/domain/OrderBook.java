package com.intelligentLibrary.dev.domain;

import java.util.Date;
/**
 * 预约图书类
 * @author Sean
 * @time 2017年5月14日
 */
public class OrderBook {
	private Integer id;			//预定id
	private User user;			//预定的用户角色
	private Book book;			//预定什么图书
	private Date borrowDate;	//预约借阅时间
	private Date returnDate;	//应当归还的日期
	private boolean orderstatus;//预定的状态
	
	public OrderBook() {
	}
	public OrderBook(User user, Book book, Date borrowDate, Date returnDate,
			boolean orderstatus) {
		this.user = user;
		this.book = book;
		this.borrowDate = borrowDate;
		this.returnDate = returnDate;
		this.orderstatus = orderstatus;
	}
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
	public Date getBorrowDate() {
		return borrowDate;
	}
	public void setBorrowDate(Date borrowDate) {
		this.borrowDate = borrowDate;
	}
	public Date getReturnDate() {
		return returnDate;
	}
	public void setReturnDate(Date returnDate) {
		this.returnDate = returnDate;
	}
	public boolean isOrderstatus() {
		return orderstatus;
	}
	public void setOrderstatus(boolean orderstatus) {
		this.orderstatus = orderstatus;
	}
	
}
