package com.docu.components.common;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class PageDO<T> implements Serializable{


	private static final long serialVersionUID = 1L;
	private int total;  
    private int totalPage;
    private int pageSize;
    private int pageNum;  
    private int startNum;
    private int endNum;
    private int offset = 5;  
    private List<Integer> pages = new ArrayList<Integer>();  
    private int pre;  
    private int next;  
    private List<T> rows;
    
    public PageDO() {
		super();
	}

	public PageDO(int pageNum,int pageSize,int total) {  
        this.pageNum = pageNum;  
        this.total = total;  
        this.pageSize = pageSize;
        this.totalPage = getTotalPage();  
        this.pages = getPages();
        this.startNum = getStartNum();
        this.endNum = getEndNum();
        this.pre = getPre();  
        this.next = getNext();  
    }  
  
    public int getTotal() {  
        return total;  
    }  
  
    public void setTotal(int total) {  
        this.total = total;  
    }  
  
    
    public int getPageSize() {
		return pageSize==0?5:pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getTotalPage() {  
        if(total%pageSize==0) {  
            totalPage = total/pageSize;  
        } else {  
            totalPage = total/pageSize + 1;  
        }  
        return totalPage;  
    }  
  
	public int getStartNum() {  
	    return (pageNum-1) * pageSize; 
	} 
	
	public int getEndNum(){
		int end = (startNum + pageSize)>total?total:(startNum + pageSize);
		return end;
	}
	  
    public int getPageNum() {  
        return pageNum;  
    }  
  
    public void setPageNum(int pageNum) {  
        this.pageNum = pageNum;  
    }  
      
    public int getOffset() {  
        return offset;  
    }  
  
    public void setOffset(int offset) {  
        this.offset = offset;  
    }  
  
    public List<Integer> getPages() {  
        pages.clear();  
        int s = (pageNum - offset)>0?(pageNum - offset):1;  
        int e = (pageNum + offset)>totalPage?totalPage:(pageNum + offset);  
        for(int i=s; i<=e; i++) {  
            pages.add(i);  
        }  
        return pages;  
    }  
  
    public void setPages(List<Integer> pages) {  
        this.pages = pages;  
    }  
  
    public int getPre() {  
        return pageNum - 1;  
    }  
  
    public void setPre(int pre) {  
        this.pre = pre;  
    }  
  
    public int getNext() {  
        return pageNum +1;  
    }  
  
    public void setNext(int next) {  
        this.next = next;  
    }

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public List<T> getRows() {
		return rows;
	}

	public void setRows(List<T> rows) {
		this.rows = rows;
	}
}
