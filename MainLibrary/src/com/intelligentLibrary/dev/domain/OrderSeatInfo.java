package com.intelligentLibrary.dev.domain;

import java.sql.Timestamp;

public class OrderSeatInfo {
	private Integer id;
	private User user;
	private Seat seat;
	private Integer punishment;
	private Timestamp ordertime;
	private Timestamp returtime;
	private Timestamp realreturn;
	public OrderSeatInfo() {
		super();
	}
	public OrderSeatInfo(Integer id, User user, Seat seat, Integer punishment, Timestamp ordertime, Timestamp returtime,
			Timestamp realreturn) {
		super();
		this.id = id;
		this.user = user;
		this.seat = seat;
		this.punishment = punishment;
		this.ordertime = ordertime;
		this.returtime = returtime;
		this.realreturn = realreturn;
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
	public Seat getSeat() {
		return seat;
	}
	public void setSeat(Seat seat) {
		this.seat = seat;
	}
	public Integer getPunishment() {
		return punishment;
	}
	public void setPunishment(Integer punishment) {
		this.punishment = punishment;
	}
	public Timestamp getOrdertime() {
		return ordertime;
	}
	public void setOrdertime(Timestamp ordertime) {
		this.ordertime = ordertime;
	}
	public Timestamp getReturtime() {
		return returtime;
	}
	public void setReturtime(Timestamp returtime) {
		this.returtime = returtime;
	}
	public Timestamp getRealreturn() {
		return realreturn;
	}
	public void setRealreturn(Timestamp realreturn) {
		this.realreturn = realreturn;
	}
	
}
