package com.intelligentLibrary.dev.domain;

import java.util.Date;
/**
 * ������
 * @author Sean
 * @time 2017��5��14��
 */
public class Comment implements java.io.Serializable {

	private Integer id;				//���۵�id
	private User user;				//���۵��û�
	private Book book;				//���۵���
	private Integer commentvalue;	//���۷���
	private Date commenttime;		//��������
	
	public Comment(Integer id, Integer commentvalue, Date commenttime, User user, Book book) {
		super();
		this.id = id;
		this.commentvalue = commentvalue;
		this.commenttime = commenttime;
		this.user = user;
		this.book = book;
	}


	public Comment() {
	}

	

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getCommentvalue() {
		return this.commentvalue;
	}

	public void setCommentvalue(Integer commentvalue) {
		this.commentvalue = commentvalue;
	}

	public Date getCommenttime() {
		return this.commenttime;
	}

	public void setCommenttime(Date commenttime) {
		this.commenttime = commenttime;
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


}