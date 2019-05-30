package com.cafe24.mysite.vo;

public class PostVo {
	private long no;
	private String title;
	private String content;
	private String reg_date;
	private long cate_no;
	
	private String blog_id;
	
	public PostVo() {
		
	}

	public long getNo() {
		return no;
	}

	public void setNo(long no) {
		this.no = no;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getReg_date() {
		return reg_date;
	}

	public void setReg_date(String reg_date) {
		this.reg_date = reg_date;
	}

	public long getCate_no() {
		return cate_no;
	}

	public void setCate_no(long cate_no) {
		this.cate_no = cate_no;
	}

	public String getBlog_id() {
		return blog_id;
	}

	public void setBlog_id(String blog_id) {
		this.blog_id = blog_id;
	}

	@Override
	public String toString() {
		return "PostVo [no=" + no + ", title=" + title + ", content=" + content + ", reg_date=" + reg_date
				+ ", cate_no=" + cate_no + ", blog_id=" + blog_id + "]";
	}


}
