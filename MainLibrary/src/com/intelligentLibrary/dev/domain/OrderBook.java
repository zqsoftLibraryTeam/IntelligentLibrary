package com.intelligentLibrary.dev.domain;

import java.util.Date;
/**
 * ԤԼͼ����
 * @author Sean
 * @time 2017��5��14��
 */
public class OrderBook {
	private Integer id;			//Ԥ��id
	private User user;			//Ԥ�����û���ɫ
	private Book book;			//Ԥ��ʲôͼ��
	private Date borrowDate;	//ԤԼ����ʱ��
	private Date returnDate;	//Ӧ���黹������
	private boolean orderstatus;//Ԥ����״̬
	
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
