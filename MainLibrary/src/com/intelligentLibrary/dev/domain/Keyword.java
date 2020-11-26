package com.intelligentLibrary.dev.domain;

import java.util.HashSet;
import java.util.Set;

/**
 * IlKeyword entity. @author MyEclipse Persistence Tools
 */

public class Keyword implements java.io.Serializable {

	// Fields

	private Integer keywordid;
	//��Ĺؼ���
	private String keycode;


	public Set<Tag> getTags() {
		return tags;
	}

	public void setTags(Set<Tag> tags) {
		this.tags = tags;
	}

	private Set<Tag> tags=new HashSet<Tag>();
	// Constructors

	/** default constructor */
	public Keyword() {
	}

	/** full constructor */

	// Property accessors

	public Integer getKeywordid() {
		return this.keywordid;
	}

	public Keyword(Integer keywordid, String keycode, Set<Tag> tags) {
		super();
		this.keywordid = keywordid;
		this.keycode = keycode;
		this.tags = tags;
	}

	public void setKeywordid(Integer keywordid) {
		this.keywordid = keywordid;
	}

	public String getKeycode() {
		return this.keycode;
	}

	public void setKeycode(String keycode) {
		this.keycode = keycode;
	}

}