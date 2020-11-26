package com.intelligentLibrary.dev.domain;

public class SingleBookBorrowInfo {
	private Book book;
	private Integer borrownumber;
	public Book getBook() {
		return book;
	}
	public void setBook(Book book) {
		this.book = book;
	}
	public Integer getBorrownumber() {
		return borrownumber;
	}
	public void setBorrownumber(Integer borrownumber) {
		this.borrownumber = borrownumber;
	}
}
