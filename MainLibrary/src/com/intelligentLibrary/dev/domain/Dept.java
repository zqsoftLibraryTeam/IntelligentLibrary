package com.intelligentLibrary.dev.domain;

import java.util.HashSet;
import java.util.Set;

/**
 * IlDept entity. @author MyEclipse Persistence Tools
 */

public class Dept implements java.io.Serializable {

	// Fields

	private Integer deptno;
	private String dname;
	private Set<User> ilUsers = new HashSet<User>();
	private Set<Weight> weights=new HashSet<Weight>();
	// Constructors

	/** default constructor */
	public Dept() {
	}


	/** full constructor */
	public Dept(String dname, Set<User> ilUsers,Set<Weight> weights) {
		this.dname = dname;
		this.ilUsers = ilUsers;
		this.weights=weights;
	}

	// Property accessors

	public Integer getDeptno() {
		return this.deptno;
	}

	public void setDeptno(Integer deptno) {
		this.deptno = deptno;
	}

	public String getDname() {
		return this.dname;
	}

	public void setDname(String dname) {
		this.dname = dname;
	}


	public Set<User> getIlUsers() {
		return ilUsers;
	}


	public void setIlUsers(Set<User> ilUsers) {
		this.ilUsers = ilUsers;
	}


	public Set<Weight> getWeights() {
		return weights;
	}


	public void setWeights(Set<Weight> weights) {
		this.weights = weights;
	}



}