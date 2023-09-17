package com.jr;

import com.google.gson.Gson;
import com.jr.entity.Emp;
import com.jr.pojo.EmpInfo;
import com.jr.service.impl.EmpServiceImpl;

import java.util.ArrayList;
import java.util.List;

public class Test {

    public static void main(String[] args) {
         EmpServiceImpl esi=new EmpServiceImpl();
        Gson gson=new Gson();
        List<Emp> list=esi.findlikeEname(new EmpInfo());
        String str=gson.toJson(list);
        String s="{\"info\":true,\"emps\":"+str+"}";
        System.out.println(str);
        System.out.println(s);

    }
}
