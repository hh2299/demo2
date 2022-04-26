package com.example.demo.common.vo;

import com.github.pagehelper.PageInfo;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class PageModel implements Serializable {


	private static final long serialVersionUID = -5620945241060559062L;
	// 当前页
	private int page;
	// 每页的数量
	private int size;

	// 总页数
	private int pages;

	private long total;

	public PageModel(PageInfo<?> pageInfo) {
		setPage(pageInfo.getPageNum());
		setPages(pageInfo.getPages());
		setSize(pageInfo.getPageSize());
		setTotal(pageInfo.getTotal());
	}


}
