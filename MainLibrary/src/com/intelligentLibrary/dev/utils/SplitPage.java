package com.intelligentLibrary.dev.utils;

public class SplitPage {
	private int objnum =AdminConfig.BOOK_SPLITNUM;//ÿҳ��������¼
	private double totalnum;//�ܼ�¼��
	private int currentpage=1;//��ǰҳ��
	private int starindex;
	
	public SplitPage(int objnum, int totalnum, int currentpage) {
		this.objnum = objnum;
		this.totalnum = totalnum;
		this.currentpage = currentpage;
	}
	public SplitPage() {
	}
	public int getObjnum() {
		return objnum;
	}
	public void setObjnum(int objnum) {
		this.objnum = objnum;
	}
	public int getTotalnum() {
		return (int) Math.ceil(totalnum);
	}
	public void setTotalnum(double totalnum) {
		this.totalnum = totalnum;
	}
	public int getCurrentpage() {
		return currentpage;
	}
	public void setCurrentpage(int currentpage) {
		this.currentpage = currentpage;
	}
	public int getStarindex() {
		starindex = objnum*(currentpage-1);
		return starindex;
	}
	
	
}
