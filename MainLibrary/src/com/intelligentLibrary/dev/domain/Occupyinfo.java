package com.intelligentLibrary.dev.domain;

import java.sql.Timestamp;

/**
 * 预定占座
 * @author Sean
 * @time 2017年5月14日
 */
public class Occupyinfo implements java.io.Serializable {

	private Integer id;
	private User user;
	private Seat seat;
	//提交预定的时间
	private Timestamp ordertime;
	//所要选择预定的时间
	private Timestamp picktime;

	public Occupyinfo() {
	}

	public Integer getId() {
		return this.id;
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

	public Occupyinfo(Integer id, User user, Seat seat, Timestamp ordertime, Timestamp picktime) {
		super();
		this.id = id;
		this.user = user;
		this.seat = seat;
		this.ordertime = ordertime;
		this.picktime = picktime;
	}

	public Timestamp getPicktime() {
		return picktime;
	}

	public void setPicktime(Timestamp picktime) {
		this.picktime = picktime;
	}

	public Seat getSeat() {
		return seat;
	}

	public void setSeat(Seat seat) {
		this.seat = seat;
	}

	public Timestamp getOrdertime() {
		return this.ordertime;
	}

	public void setOrdertime(Timestamp ordertime) {
		this.ordertime = ordertime;
	}

}