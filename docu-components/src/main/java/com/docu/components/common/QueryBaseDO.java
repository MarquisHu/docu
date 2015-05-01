package com.docu.components.common;

import java.io.Serializable;

public abstract class QueryBaseDO  implements Serializable{

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    public static final int DEFAULT_PAGE_SIZE = 10;
    public static final int DEFAULT_PAGE_NO = 1;
    public static final int MAX_PAGE_SIZE = 5000;
    private Integer mod;
    private Integer left;
    private int pageSize = DEFAULT_PAGE_SIZE;
    private int pageNum = DEFAULT_PAGE_NO;
    private int total;
    private boolean needPagination = true;
    private boolean needPageTotal = true;

    public int getTotalPage(){
    	int page=this.getTotal()/this.getPageSize();
    	int standTotalNum=this.getPageSize()*page;
    	if(this.getTotal()>standTotalNum)
    		return page+1;
    	return page;
    }
    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getPageSize() {
        if (pageSize <= 0) {
            pageSize = DEFAULT_PAGE_SIZE;
        }
        return pageSize;
    }


    public void setPageSize(int pageSize) {
        if (pageSize < 0) {
            pageSize = DEFAULT_PAGE_SIZE;
        }
        if (pageSize > MAX_PAGE_SIZE) {
            pageSize = MAX_PAGE_SIZE;
        }
        this.pageSize = pageSize;
    }

    public void setMaxPageSize() {
        this.pageSize = MAX_PAGE_SIZE;
        setNeedPageTotal(false);
        setNeedPagination(false);
    }

    public int getPageNum() {
        if (Integer.MAX_VALUE != total && total > 0) {
            pageNum = Math.min(pageNum, (int) Math.ceil((double) total / pageSize));
        }

        if (pageNum <= 0) {
            pageNum = DEFAULT_PAGE_NO;
        }
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        if (pageNum <= 0) {
            pageNum = DEFAULT_PAGE_NO;
        }
        this.pageNum = pageNum;
    }

    public int getStartNum() {
        int startNum = getPageSize() * (getPageNum() - 1) + 1;
        if (startNum < 1) {
            startNum = 1;
        }
        return startNum;
    }

    public int getPreEndNum() {
        return getPageSize() * (getPageNum() - 1);
    }

    public int getEndNum() {
        int endNum = getPageSize() * getPageNum();
        if (endNum < 0) {
            endNum = 1;
        }
        endNum = Math.min(endNum, total);
        return endNum;
    }

    /**
     * Getter
     * @return needPagination
     */
    public boolean isNeedPagination() {
        return needPagination;
    }

    /**
     * Setter
     * @param needPagination
     */
    public void setNeedPagination(boolean needPagination) {
        this.needPagination = needPagination;
    }

    /**
     * Getter
     * @return needPageTotal
     */
    public boolean isNeedPageTotal() {
        return needPageTotal;
    }

    /**
     * Setter
     * @param needPageTotal
     */
    public void setNeedPageTotal(boolean needPageTotal) {
        this.needPageTotal = needPageTotal;
    }

    public Integer getMod() {
        return mod;
    }

    public void setMod(Integer mod) {
        this.mod = mod;
    }

    public Integer getLeft() {
        return left;
    }

    public void setLeft(Integer left) {
        this.left = left;
    }
}
