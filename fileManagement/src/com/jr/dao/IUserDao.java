package com.jr.dao;

import com.jr.entity.User;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;

/**
 * 这个接口规定了对于User对应数据库表我们能有的行为
 * */
public interface IUserDao {

    int insertUser(User user) throws SQLException;

    /**
     * 将要登陆的对象作为参数传递与数据库里的属性进行比较
     * */
    User selectUser(User user) throws InvocationTargetException, SQLException, InstantiationException, IllegalAccessException, NoSuchMethodException;

}
