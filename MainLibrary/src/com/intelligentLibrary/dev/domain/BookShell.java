package com.intelligentLibrary.dev.domain;

import java.util.HashSet;
import java.util.Set;

/**
 * 书架类
 * @author Sean
 * @time 2017年5月14日
 */
public class BookShell implements java.io.Serializable {

	private Integer shellid;	//书架
	private String loc;			//书架位置
	private Set<Book> ilBooks = new HashSet<Book>();//书架上有哪些书

	public BookShell() {
	}

	public BookShell(String loc, Set<Book> ilBooks) {
		this.loc = loc;
		this.ilBooks = ilBooks;
	}

	public Integer getShellid() {
		return this.shellid;
	}

	public void setShellid(Integer shellid) {
		this.shellid = shellid;
	}

	public String getLoc() {
		return this.loc;
	}

	public void setLoc(String loc) {
		this.loc = loc;
	}

	public Set<Book> getIlBooks() {
		return ilBooks;
	}

	public void setIlBooks(Set<Book> ilBooks) {
		this.ilBooks = ilBooks;
	}


}