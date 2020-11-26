package com.intelligentLibrary.dev.domain;

import java.util.Date;

/**
 * ������
 * @author Sean
 * @time 2017��5��14��
 */
public class BorrowInfo implements java.io.Serializable {

	private Integer id;		//����id
	private User ilUser;	//���ĵ��û�
	private Book ilBook;	//�����ı���
	private Integer fine;	//����
	private Date borrowdate;//ʵ�ʽ���ʱ��
	private Date returndate;//Ӧ������
	private Date realreturn;//ʵ�ʹ黹ʱ��

	public BorrowInfo() {
	}
	/**
	 * �û�ʵ�ʽ赽��ʱ���ù��췽��
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