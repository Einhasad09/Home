package com.jr.dao.Impl;

import com.jr.dao.IUserDao;
import com.jr.entity.User;
import com.jr.util.BaseDao;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.List;

public class UserDaoImpl implements IUserDao {

    private BaseDao baseDao = new BaseDao();
    @Override
    public int insertUser(User user) throws SQLException {
        String sql = "insert into db_user(userName,passWord) values(?,?)";
        Object[] objs = {user.getUserName(),user.getPassWord()};
        return baseDao.updateInfo(sql,objs);
    }

    @Override
    public User selectUser(User user) throws InvocationTargetException, SQLException, InstantiationException, IllegalAccessException, NoSuchMethodException {
        String sql = "select * from db_user where userName=? and passWord=?";
        Object[] objs = {user.getUserName(),user.getPassWord()};
        List list = null;
        list = baseDao.queryInfo(user.getClass(),sql,objs);
        return list.size()!=0? (User) list.get(0) :null;
    }
}
