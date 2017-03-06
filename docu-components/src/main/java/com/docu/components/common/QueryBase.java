package com.docu.components.common;

public class QueryBase extends QueryBaseDO{
	/**
	 * 
	 */
	private static final long serialVersionUID = 6183151763191290585L;
	private Object entity;

    public Object getEntity() {
        return entity;
    }

    public void setEntity(Object entity) {
        this.entity = entity;
    }

    public QueryBase(int pageNum,Object entity) {
        super.setPageNum(pageNum);
        this.entity = entity;
    }
}
