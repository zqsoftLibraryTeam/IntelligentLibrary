package com.intelligentLibrary.dev.domain;

import java.util.HashSet;
import java.util.Set;

/**
 * 书本类型
 * @author Sean
 * @time 2017年5月14日
 */
public class BookSort implements java.io.Serializable {

	private Integer sortid;		//类型id
	private BookSort ilBooksort;//父分类
	private String sortname;	//分类名称
	private Integer booknumber;	//这个类别下有多少书
	private Set<Book> ilBooks = new HashSet<Book>();//这个分类下的所有书
	private Set<BookSort> ilBooksorts = new HashSet<BookSort>();//书本子分类
	private Set<Weight> weights = new HashSet<Weight>();//权重
	
	public BookSort() {
	}
	
	public BookSort(Integer sortid, BookSort ilBooksort, String sortname, Integer booknumber, Set<Book> ilBooks,
			Set<BookSort> ilBooksorts, Set<Weight> weights) {
		this.sortid = sortid;
		this.ilBooksort = ilBooksort;
		this.sortname = sortname;
		this.booknumber = booknumber;
		this.ilBooks = ilBooks;
		this.ilBooksorts = ilBooksorts;
		this.weights = weights;
	}

	public Integer getSortid() {
		return sortid;
	}
	public void setSortid(Integer sortid) {
		this.sortid = sortid;
	}
	public BookSort getIlBooksort() {
		return ilBooksort;
	}
	public void setIlBooksort(BookSort ilBooksort) {
		this.ilBooksort = ilBooksort;
	}
	public String getSortname() {
		return sortname;
	}
	public void setSortname(String sortname) {
		this.sortname = sortname;
	}
	public Integer getBooknumber() {
		return booknumber;
	}
	public void setBooknumber(Integer booknumber) {
		this.booknumber = booknumber;
	}
	public Set<Book> getIlBooks() {
		return ilBooks;
	}
	public void setIlBooks(Set<Book> ilBooks) {
		this.ilBooks = ilBooks;
	}
	public Set<BookSort> getIlBooksorts() {
		return ilBooksorts;
	}
	public void setIlBooksorts(Set<BookSort> ilBooksorts) {
		this.ilBooksorts = ilBooksorts;
	}
	public Set<Weight> getWeights() {
		return weights;
	}
	public void setWeights(Set<Weight> weights) {
		this.weights = weights;
	}
	
}