package com.intelligentLibrary.dev.domain;

import java.util.Date;

/**
 * 借阅类
 * @author Sean
 * @time 2017年5月14日
 */
public class BorrowInfo implements java.io.Serializable {

	private Integer id;		//借阅id
	private User ilUser;	//借阅的用户
	private Book ilBook;	//借阅哪本书
	private Integer fine;	//罚金
	private Date borrowdate;//实际借阅时间
	private Date returndate;//应还日期
	private Date realreturn;//实际归还时间

	public BorrowInfo() {
	}
	/**
	 * 用户实际借到书时调用构造方法
	 * @param ilUser
	 * @param ilBook
	 * @param borrowdate
	 * @param returndate
	 */
	public BorrowInfo(User ilUser,Book ilBook,Date borrowdate,Date returndate){
		this.ilUser = ilUser;
		this.ilBook = ilBook;
		this.borrowdate = borrowdate;
		this.returndate = returndate;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public User getIlUser() {
		return this.ilUser;
	}

	public void setIlUser(User ilUser) {
		this.ilUser = ilUser;
	}

	public Book getIlBook() {
		return this.ilBook;
	}

	public void setIlBook(Book ilBook) {
		this.ilBook = ilBook;
	}

	public Integer getFine() {
		return this.fine;
	}

	public void setFine(Integer fine) {
		this.fine = fine;
	}

	public Date getBorrowdate() {
		return this.borrowdate;
	}

	public void setBorrowdate(Date borrowdate) {
		this.borrowdate = borrowdate;
	}

	public Date getReturndate() {
		return this.returndate;
	}
	
	public void setReturndate(Date returndate) {
		this.returndate = returndate;
	}

	public Date getRealreturn() {
		return realreturn;
	}

	public void setRealreturn(Date realreturn) {
		Long spilth = realreturn.getTime()-borrowdate.getTime();
		this.fine = (int) (spilth%(1000*60*60*24));
		this.realreturn = realreturn;
	}

}