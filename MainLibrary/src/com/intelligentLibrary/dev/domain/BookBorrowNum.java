package com.intelligentLibrary.dev.domain;
/**
 * ��ȡ�����鱾�Ľ�����
 * @author Sean
 * @time 2017��5��14��
 */
public class BookBorrowNum {
	private int bookid;	//������鱾id
	private int num;	//����Ľ�����
	
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
