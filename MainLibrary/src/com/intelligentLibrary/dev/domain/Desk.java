package com.intelligentLibrary.dev.domain;

/**
 * 桌子类
 * @author Sean
 * @time 2017年5月14日
 */
public class Desk implements java.io.Serializable {

	private Integer id;		//桌子id
	private Integer floor;	//桌子所在楼层
	private String position;//桌子位置
	private Integer desknum;//桌子编号
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