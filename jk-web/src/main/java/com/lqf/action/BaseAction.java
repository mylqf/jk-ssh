package com.lqf.action;

import com.lqf.domain.User;
import com.lqf.utils.SysConstant;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.interceptor.ApplicationAware;
import org.apache.struts2.interceptor.RequestAware;
import org.apache.struts2.interceptor.SessionAware;

import java.util.Map;

public class BaseAction extends ActionSupport implements RequestAware,SessionAware,ApplicationAware{


    protected Map<String, Object> request;
    protected Map<String, Object> session;
    protected Map<String, Object> application;

    public Map<String, Object> getRequest() {
        return request;
    }

    public Map<String, Object> getSession() {
        return session;
    }

    public Map<String, Object> getApplication() {
        return application;
    }

    @Override
    public void setApplication(Map<String, Object> application) {
        this.application=application;
    }

    @Override
    public void setRequest(Map<String, Object> request) {
        this.request=request;
    }

    @Override
    public void setSession(Map<String, Object> session) {
        this.session=session;
    }

    //将对象放入值栈的栈顶
    public void push(Object obj){
        ActionContext.getContext().getValueStack().push(obj);
    }

    public void put(String key,Object value){
        ActionContext.getContext().put(key,value);
    }

    //获取当前登入用户的信息
//    public User getCurUser(){
//        User user= (User) session.get(SysConstant.CURRENT_USER_INFO);
//        return user;
//    }

}
