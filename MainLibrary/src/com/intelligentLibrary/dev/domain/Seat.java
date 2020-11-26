package com.intelligentLibrary.dev.domain;

import java.util.HashSet;
import java.util.Set;

/**
 * ��λ��
 * @author Sean
 * @time 2017��5��14��
 */
public class Seat implements java.io.Serializable {


	private Integer id;		//��λid
	private Desk desk;		//��Ϊ����������
	private Short occupied;	//�ж��Ƿ�ռ��
	private String position;//λ��
	
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