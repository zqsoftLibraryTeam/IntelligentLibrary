package com.intelligentLibrary.dev.domain;

/**
 * ������
 * @author Sean
 * @time 2017��5��14��
 */
public class Desk implements java.io.Serializable {

	private Integer id;		//����id
	private Integer floor;	//��������¥��
	private String position;//����λ��
	private Integer desknum;//���ӱ��
	public Desk() {
	}

	public Desk(Integer floor, String position, Integer desknum) {
		this.floor = floor;
		this.position = position;
		this.desknum = desknum;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getFloor() {
		return this.floor;
	}

	public void setFloor(Integer floor) {
		this.floor = floor;
	}

	public String getPosition() {
		return this.position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public Integer getDesknum() {
		return this.desknum;
	}

	public void setDesknum(Integer desknum) {
		this.desknum = desknum;
	}

}