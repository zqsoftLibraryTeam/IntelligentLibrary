package com.intelligentLibrary.dev.domain;

import java.util.HashSet;
import java.util.Set;

/**
 * 座位类
 * @author Sean
 * @time 2017年5月14日
 */
public class Seat implements java.io.Serializable {


	private Integer id;		//座位id
	private Desk desk;		//作为所属的桌子
	private Short occupied;	//判断是否被占据
	private String position;//位置
	
	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	private Set<Occupyinfo> occupyinfos=new HashSet<Occupyinfo>();

	private Set<OrderSeatInfo> orderSeatInfos=new HashSet<OrderSeatInfo>();
	
	// Constructors

	public Set<OrderSeatInfo> getOrderSeatInfos() {
		return orderSeatInfos;
	}

	public void setOrderSeatInfos(Set<OrderSeatInfo> orderSeatInfos) {
		this.orderSeatInfos = orderSeatInfos;
	}

	public Set<Occupyinfo> getOccupyinfos() {
		return occupyinfos;
	}

	public void setOccupyinfos(Set<Occupyinfo> occupyinfos) {
		this.occupyinfos = occupyinfos;
	}

	/** default constructor */
	public Seat() {
	}







	public Seat(Integer id, Desk desk, Short occupied, String position, Set<Occupyinfo> occupyinfos,
			Set<OrderSeatInfo> orderSeatInfos) {
		super();
		this.id = id;
		this.desk = desk;
		this.occupied = occupied;
		this.position = position;
		this.occupyinfos = occupyinfos;
		this.orderSeatInfos = orderSeatInfos;
	}

	public Desk getDesk() {
		return desk;
	}

	public void setDesk(Desk desk) {
		this.desk = desk;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}


	public Short getOccupied() {
		return this.occupied;
	}

	public void setOccupied(Short occupied) {
		this.occupied = occupied;
	}

}