package com.intelligentLibrary.dev.domain;

import java.util.Date;
/**
 * 评论类
 * @author Sean
 * @time 2017年5月14日
 */
public class Comment implements java.io.Serializable {

	private Integer id;				//评论的id
	private User user;				//评论的用户
	private Book book;				//评论的书
	private Integer commentvalue;	//评论分数
	private Date commenttime;		//评论日期
	
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