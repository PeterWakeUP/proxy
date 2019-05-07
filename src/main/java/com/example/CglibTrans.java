package com.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.DriverManager;

public class CglibTrans implements MethodInterceptor {

    Logger logger = LoggerFactory.getLogger(getClass());

    public Object getInstance(Class clazz) throws Exception{

        Enhancer enhancer = new Enhancer();

        enhancer.setSuperclass(clazz);

        enhancer.setCallback(this);

        return enhancer.create();
    }

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        if(!method.isAnnotationPresent(Affair.class)){
            methodProxy.invokeSuper(o, objects);
            return null;
        }

        String url = "jdbc:mysql://127.0.0.1:3306/zz_my?allowMultiQueries=true&useUnicode=true&characterEncoding=UTF-8&useSSL=false&serverTimezone=GMT%2B8";
        String username = "root";
        String password = "admin";
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection conn= DriverManager.getConnection(url, username, password);

        try{

            conn.setAutoCommit(false);
            methodProxy.invokeSuper(o, objects);
            conn.commit();
        }catch (Exception e){
            conn.rollback();
            logger.info("事务异常",e);
        }finally {
            conn.close();
        }

        return null;
    }
}
