package com.onest.webshop.bean;

public class Page {
	private Integer totalcount;//总条数
	private Integer pagecount=3;//每页显示的条数
	private Integer totalpage;//总页数
	private Integer dpage;//当前显示的页码
	public Integer getTotalcount() {
		return totalcount;
	}
	public void setTotalcount(Integer totalcount) {
		this.totalcount = totalcount;
	}
	public Integer getPagecount() {
		return pagecount;
	}
	public void setPagecount(Integer pagecount) {
		this.pagecount = pagecount;
	}
	public Integer getTotalpage() {
		return totalpage;
	}
	public void setTotalpage() {
		if(totalcount%pagecount==0) {
			this.totalpage = totalcount/pagecount;
		}else {
			this.totalpage = totalcount/pagecount+1;
			
		}
	}
	public Integer getDpage() {
		return dpage;
	}
	public void setDpage(Integer dpage) {
		this.dpage = dpage;
	}
	
}
