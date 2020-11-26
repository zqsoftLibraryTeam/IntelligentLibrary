package com.intelligentLibrary.dev.domain;
/**
 * 获取所有书本的借阅数
 * @author Sean
 * @time 2017年5月14日
 */
public class BookBorrowNum {
	private int bookid;	//此书的书本id
	private int num;	//此书的借阅数
	
	public BookBorrowNum() {
	}
	
	public BookBorrowNum(int bookid, int num) {
		this.bookid = bookid;
		this.num = num;
	}

	public int getBookid() {
		return bookid;
	}
	public void setBookid(int bookid) {
		this.bookid = bookid;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}

}
