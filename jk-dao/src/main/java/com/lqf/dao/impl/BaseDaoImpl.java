package com.lqf.dao.impl;

import com.lqf.dao.BaseDao;
import com.lqf.utils.Page;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

public class BaseDaoImpl implements BaseDao {

    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public Session getSession(){
        return sessionFactory.getCurrentSession();
    }


    //带条件查询
    @Override
    public <T> List<T> find(String hql, Class<T> entityClass, Object[] params) {

        Query query=this.getSession().createQuery(hql);
        if (params!=null){
            for (int i=0;i<params.length;i++){
                query.setParameter(i,params);
            }
        }
        return (List<T>)query.list();
    }

    //获取一条，根据主键id；
    @Override
    public <T> T get(Class<T> entityClass, Serializable id) {
        return (T)this.getSession().get(entityClass,id);
    }

    //分页查询，查询两次，一次查询总数，一次查询分页记录
    @Override
    public <T> Page<T> findPage(String hql, Page<T> page, Class<T> entityClass, Object[] params) {

        Query query=this.getSession().createQuery(hql);
        if (params!=null){

                for (int i=0;i<params.length;i++) {
                    query.setParameter(i, params[i]);
                }
        }
        //查询一次，获取记录总数
        query.setFirstResult((page.getPageNo()-1)*page.getPageSize()); //设置开始位置
        query.setMaxResults(page.getPageNo()); //设置获取几条
        page.setResults((List<T>)query.list());

        return  page;
    }


    //新增和修改，hibernate根据id是否为null自动判断
    @Override
    public <T> void saveorUpdate(T entity) {
        this.getSession().saveOrUpdate(entity);
    }

    //集合保存，这时新增还是修改，就自动判断，调用时是否简洁。适合批量新增和修改时。
    @Override
    public <T> void saveOrUpdateAll(Collection<T> entitys) {

        for (T entity :entitys){
            this.saveorUpdate(entity);
        }
    }

    //按主键id删除
    @Override
    public <T> void deleteById(Class<T> entityClass, Serializable id) {
        this.getSession().delete(get(entityClass,id));
    }

    //批量删除
    @Override
    public <T> void delete(Class<T> entityClass, Serializable[] ids) {
        for (Serializable s:ids){
            deleteById(entityClass,s);
        }
    }
}
