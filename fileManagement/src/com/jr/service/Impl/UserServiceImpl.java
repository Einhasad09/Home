package com.jr.service.Impl;

import com.jr.dao.IUserDao;
import com.jr.dao.Impl.UserDaoImpl;
import com.jr.entity.User;
import com.jr.service.IUserService;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;

public class UserServiceImpl implements IUserService {
    private IUserDao iud = new UserDaoImpl();
    @Override
    public boolean regUser(User user) throws SQLException {
        return iud.insertUser(user)==0 ? false : true;

    }

    @Override
    public User login(User user) throws SQLException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
        return iud.selectUser(user);
    }
}
