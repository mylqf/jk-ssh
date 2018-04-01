package com.lqf.dao.springdao;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class SqlDao {

    private static Logger log=Logger.getLogger(SqlDao.class);
    private JdbcTemplate jdbcTemplate;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    //返回单值
    public String getSingleValue(String sql){
        log.debug(sql);
        StringBuffer sBuf=new StringBuffer();
        List jlist=jdbcTemplate.queryForList(sql);
        Iterator ite=jlist.iterator();
        while (ite.hasNext()){
            Map map=(Map)ite.next();
            for (Object o:map.keySet()){
                sBuf.append(String.valueOf(map.get(o))).append(",");
            }
        }
        if (sBuf!=null && sBuf.length()>1){
            sBuf.delete(sBuf.length()-1,sBuf.length());
        }
        return sBuf.toString();
    }
}
