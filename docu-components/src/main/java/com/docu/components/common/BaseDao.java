package com.docu.components.common;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;

import com.docu.components.util.NumberUtils;

public abstract class BaseDao {

    private SqlSessionTemplate sqlMapClientNormal;

    public SqlSessionTemplate getSqlSessionTemplate() {
        return sqlMapClientNormal;
    }

    public void setSqlSessionTemplate(SqlSessionTemplate sqlMapClientNormal) {
        this.sqlMapClientNormal = sqlMapClientNormal;
    }
    
    @SuppressWarnings("rawtypes")
    protected List queryPageData(QueryBaseDO query, String countSql, String dataSql) {
        List result = null;
        if (query.isNeedPageTotal()) {
            Integer count = (Integer) getSqlSessionTemplate().selectOne(countSql, query);
            if (NumberUtils.isNullOrZero(count)) {
                query.setTotal(0);
            } else {
                query.setTotal(count);
                result = getSqlSessionTemplate().selectList(dataSql, query);
            }
        } else {
            result = getSqlSessionTemplate().selectList(dataSql, query);
        }
        return result;
    }
}
