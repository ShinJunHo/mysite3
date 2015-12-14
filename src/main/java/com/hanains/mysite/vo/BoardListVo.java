package com.hanains.mysite.vo;

public class BoardListVo {
	private Long no;
	private String title;
	private Long member_no;
	private String name;
	private Long view_cnt;
	private String reg_date;
	public Long getNo() {
		return no;
	}
	public void setNo(Long no) {
		this.no = no;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Long getMember_no() {
		return member_no;
	}
	public void setMember_no(Long member_no) {
		this.member_no = member_no;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Long getView_cnt() {
		return view_cnt;
	}
	public void setView_cnt(Long view_cnt) {
		this.view_cnt = view_cnt;
	}
	public String getReg_date() {
		return reg_date;
	}
	public void setReg_date(String reg_date) {
		this.reg_date = reg_date;
	}
	@Override
	public String toString() {
		return "BoardListVo [no=" + no + ", title=" + title + ", member_no="
				+ member_no + ", name=" + name + ", view_cnt=" + view_cnt
				+ ", reg_date=" + reg_date + "]";
	}
	
	
}
