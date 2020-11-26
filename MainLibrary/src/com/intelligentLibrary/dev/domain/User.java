package com.intelligentLibrary.dev.domain;

import java.util.HashSet;
import java.util.Set;

/**
 * �û�����
 * @author Sean
 * @time 2017��5��14��
 */
public class User implements java.io.Serializable {
	
	private Integer userid;		//�û�id
	private Dept ilDept;		//�����ݵ�����
	private String username;	//�û���
	private String password;	//����
	private String sex;			//�Ա�
	private String describtion;	//����
	private String email;		//����
	private String phone;		//�绰
	private String name;		//����
	private Integer timelimit;	//����Լ��ʱ��
	private Integer age;		//����
	private String identify;	//��ʶ
	

	private Set<BorrowInfo> ilBorrowinfos = new HashSet<BorrowInfo>();		//�����鱾��Ϣ
	private Set<OrderBook> orderbooks=new HashSet<OrderBook>();				//�����鱾��Ϣ
	private Set<Comment> comments=new HashSet<Comment>();					//������Ϣ
	private Set<Occupyinfo> occupyinfos=new HashSet<Occupyinfo>();			//Ԥ��ռ����Ϣ
	private Set<Recommandinfo> recommandinfos=new HashSet<Recommandinfo>();	//�Ƽ���Ϣ
	private Set<OrderSeatInfo> orderSeatInfos=new HashSet<OrderSeatInfo>();	//��ʷԤ����Ϣ

	public User() {
	}

	public User(Integer userid, Dept ilDept, String username, String password, String sex, String describtion,
			String email, String phone, String name, Integer timelimit, Integer age, String identify,
			Set<BorrowInfo> ilBorrowinfos, Set<OrderBook> orderbooks, Set<Comment> comments,
			Set<Occupyinfo> occupyinfos, Set<Recommandinfo> recommandinfos, Set<OrderSeatInfo> orderSeatInfos) {
		super();
		this.userid = userid;
		this.ilDept = ilDept;
		this.username = username;
		this.password = password;
		this.sex = sex;
		this.describtion = describtion;
		this.email = email;
		this.phone = phone;
		this.name = name;
		this.timelimit = timelimit;
		this.age = age;
		this.identify = identify;
		this.ilBorrowinfos = ilBorrowinfos;
		this.orderbooks = orderbooks;
		this.comments = comments;
		this.occupyinfos = occupyinfos;
		this.recommandinfos = recommandinfos;
		this.orderSeatInfos = orderSeatInfos;
	}

	public Integer getUserid() {
		return userid;
	}


	public void setUserid(Integer userid) {
		this.userid = userid;
	}


	public Dept getIlDept() {
		return ilDept;
	}


	public void setIlDept(Dept ilDept) {
		this.ilDept = ilDept;
	}


	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public String getSex() {
		return sex;
	}


	public void setSex(String sex) {
		this.sex = sex;
	}


	public String getDescribtion() {
		return describtion;
	}


	public void setDescribtion(String describtion) {
		this.describtion = describtion;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getPhone() {
		return phone;
	}


	public void setPhone(String phone) {
		this.phone = phone;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public Integer getTimelimit() {
		return timelimit;
	}


	public void setTimelimit(Integer timelimit) {
		this.timelimit = timelimit;
	}


	public Integer getAge() {
		return age;
	}


	public void setAge(Integer age) {
		this.age = age;
	}


	public String getIdentify() {
		return identify;
	}


	public void setIdentify(String identify) {
		this.identify = identify;
	}


	public Set<BorrowInfo> getIlBorrowinfos() {
		return ilBorrowinfos;
	}


	public void setIlBorrowinfos(Set<BorrowInfo> ilBorrowinfos) {
		this.ilBorrowinfos = ilBorrowinfos;
	}


	public Set<OrderBook> getOrderbooks() {
		return orderbooks;
	}


	public void setOrderbooks(Set<OrderBook> orderbooks) {
		this.orderbooks = orderbooks;
	}


	public Set<Comment> getComments() {
		return comments;
	}


	public void setComments(Set<Comment> comments) {
		this.comments = comments;
	}


	public Set<Occupyinfo> getOccupyinfos() {
		return occupyinfos;
	}


	public void setOccupyinfos(Set<Occupyinfo> occupyinfos) {
		this.occupyinfos = occupyinfos;
	}


	public Set<Recommandinfo> getRecommandinfos() {
		return recommandinfos;
	}


	public void setRecommandinfos(Set<Recommandinfo> recommandinfos) {
		this.recommandinfos = recommandinfos;
	}


	public Set<OrderSeatInfo> getOrderSeatInfos() {
		return orderSeatInfos;
	}


	public void setOrderSeatInfos(Set<OrderSeatInfo> orderSeatInfos) {
		this.orderSeatInfos = orderSeatInfos;
	}


	@Override
	  public boolean equals(Object obj) {
	     if(this == obj)
	         return true;
	     if(obj == null)
	         return false;
	     if(!(obj instanceof User))
	         return false;
	     User other = (User)obj;
	     if(username == null && password == null){
	    	 return false;
	     }else if(other.username !=null && other.password !=null){
	    	 if(username.equals(other.username) && password.equals(other.password))
		         return true;
	     }
	     return false;
	  }

}