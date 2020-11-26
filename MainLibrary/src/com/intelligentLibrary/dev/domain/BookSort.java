package com.intelligentLibrary.dev.domain;

import java.util.HashSet;
import java.util.Set;

/**
 * �鱾����
 * @author Sean
 * @time 2017��5��14��
 */
public class BookSort implements java.io.Serializable {

	private Integer sortid;		//����id
	private BookSort ilBooksort;//������
	private String sortname;	//��������
	private Integer booknumber;	//���������ж�����
	private Set<Book> ilBooks = new HashSet<Book>();//��������µ�������
	private Set<BookSort> ilBooksorts = new HashSet<BookSort>();//�鱾�ӷ���
	private Set<Weight> weights = new HashSet<Weight>();//Ȩ��
	
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