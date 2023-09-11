package com.jr.service;

import com.jr.entity.User;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;

public interface IUserService {
    /*
     * 在main里传入一个user对象，返回一个Boolean告诉用户是否注册/添加成功
     * @param stu  true:注册/添加 成功
     *             false:注册/添加 失败
     * */
    boolean regUser(User user) throws SQLException;

    /*
     * @param user: 登陆失败null
     *             登陆成功不为null*/
    User login(User user) throws SQLException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException;

}
