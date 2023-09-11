package com.jr.test;

import com.jr.entity.File01;
import com.jr.entity.Program;
import com.jr.entity.User;
import com.jr.service.Impl.FileServiceImpl;
import com.jr.service.Impl.ProgramServiceImpl;
import com.jr.service.Impl.UserServiceImpl;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.Scanner;

/**
 * @author
 * @date
 * */
public class Login {

    /**
     * 主界面
     * */
    public void login1() {
        System.out.println("******************************************************************************");
        System.out.println("*************************** 文 件 管 理 系 统 *********************************");
        System.out.println("******************************************************************************");
        System.out.println("********************1.注册    2.登陆    0.退出**********************************");
        System.out.println("******************************************************************************");
        System.out.println("__________________________请选择您的操作序号：___________________________________");
        Scanner input = new Scanner(System.in);
        int select = input.nextInt();
        if(select!=1 && select!=2 && select!=0){
            System.out.println("输入有误，请重新输入：");
            select = input.nextInt();
        }
        if(select == 0){
            System.exit(1);
        }
        if (select == 1){
            regDO();
        }
        if (select == 2){
            loginDO01();
        }

    }

    /**
     * 注册界面
     * */
    public void regDO() {
        Scanner input = new Scanner(System.in);
        UserServiceImpl usi = new UserServiceImpl();
        ProgramServiceImpl psi = new ProgramServiceImpl();
        FileServiceImpl fsi = new FileServiceImpl();
        System.out.println("_______注册______");
        System.out.println("请输入姓名：");
        String userName = input.next();
        System.out.println("请输入密码：");
        String userPassword = input.next();
        System.out.println("请再次输入密码：");
        String userPassword1 = input.next();
        while(!userPassword.equals(userPassword1)){
            System.out.println("两次密码不同，请重新输入密码！");
            userPassword1 = input.next();
        }
        User user = new User(userName,userPassword);
        try {
            boolean b = usi.regUser(user);
            if(b){
                System.out.println("注册成功！\n " +
                        "请问要直接登陆嘛！\n" +
                        "按1登陆 按2退出");
                int select = input.nextInt();
                switch (select){
                    case 1:
                        loginDO01();
                        break;
                    case 2:
                        System.exit(1);
                }
            }else{
                System.out.println("注册失败，数据库中有本条数据");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("注册失败，数据库中有本条记录");
        }
    }

    /**
     * 登陆 界面——输入账号密码
     * */
    public void loginDO01() {
        Scanner input = new Scanner(System.in);
        System.out.println("__________请输入账号__________：");
        String userName11 = input.next();
        System.out.println("__________请输入密码__________：");
        String userPassword11 = input.next();

            loginDo02(userName11,userPassword11);



    }

    /**
     * 登陆 账号密码判断
     * */
    public void loginDo02(String userName11,String userPassword11) {
        Scanner input = new Scanner(System.in);
        UserServiceImpl usi = new UserServiceImpl();
        User user1 = new User(userName11,userPassword11);
        User login1 = null;
        try {
            login1 = usi.login(user1);
            if(login1!=null){
                if(login1!=null){
                    System.out.println("登陆成功！");
                    System.out.println("请选择 1.应用程序管理 或者 2.文件管理");
                    System.out.println("请输入你的选择:");
                    int select = input.nextInt();
                    if(select>2 && select<1){
                        System.out.println("请重新输入");
                    }else{
                        if(select==1){
                            programDo();
                        }else{
                            fileDo();
                        }
                    }
                }else{
                    System.exit(1);
                }
            }else{
                System.out.println("账号密码有误，请重新登陆！");
                loginDO01();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

    }

    /**
     * 登陆 选择一条·分支
     * */
    public void loginDo021() {
        Scanner input = new Scanner(System.in);
        UserServiceImpl usi = new UserServiceImpl();

        System.out.println("请选择 1.应用程序管理 或者 2.文件管理");
        System.out.println("请输入你的选择:");
        int select = input.nextInt();
        if(select>2 && select<1){
            System.out.println("请重新输入");
        }else{
            if(select==1){
                programDo();
            }else{
                fileDo();
            }
        }

    }

    /**
     * 应用程序管理
     * */
    public void programDo() {
        Scanner input = new Scanner(System.in);
        UserServiceImpl usi = new UserServiceImpl();
        ProgramServiceImpl psi = new ProgramServiceImpl();
        FileServiceImpl fsi = new FileServiceImpl();
        System.out.println("--欢迎来到应用程序管理：");
        System.out.println("--返回上一层请按1  继续应用程序管理请按2  退出请按0");
        int select = input.nextInt();
        if(select==1){
            loginDo021();
        }else if(select==2){
            programDoSelect();
        }else if(select==0){
            System.exit(1);
        }

    }

    /**
     * 应用程序管理 选择一条·分支
     */
    public void programDoSelect() {
        Scanner input = new Scanner(System.in);

        System.out.println("|————————————————————————————————————————————————————————|");
        System.out.println("|————————1.载入程序—————2.程序检索—————3.返回上一级—————————|");
        System.out.println("|————————————————————————————————————————————————————————|");
        System.out.println("--请输入你的选择:");
        int select = input.nextInt();
        if(select!=1&&select!=2&&select!=3){
            programDoSelect();
        }
        if (select==1){
            programLoad();
        }
        if (select==2){
            programSelect();
        }
        if(select==3){
            loginDo021();
        }
    }

    /**
     * 应用程序管理 分支-程序加载
     */
    public void programLoad() {
        Scanner input = new Scanner(System.in);
        ProgramServiceImpl psi = new ProgramServiceImpl();
        System.out.println("--欢迎选择 程序加载 ");
        System.out.println("--返回上一层请输入1  继续程序加载请输入2  退出请输入0");
        int select = input.nextInt();
        if(select>2 || select<0){
            System.out.println("--请重新输入：");
            select = input.nextInt();
        }else if(select==1){
            programDoSelect();
        }else if(select==2){
            System.out.println("请输入programName:");
            String programName = input.next();
            System.out.println("请输入programPath:");
            String programPath = input.next();
            Program program = new Program(programName,programPath);
            boolean b = psi.insertProgramReal(program);
            if(b){
                System.out.println("程序加载成功");
                programDoSelect();
            }else{
                System.out.println("程序加载失败！");
                programDoSelect();
            }
        }else if(select==0){
            System.exit(1);
        }
    }

    /**
     * 应用程序管理 分支-程序检索 通过程序名修改文件类型+展示文件
     */
    public void programSelect() {
        Scanner input = new Scanner(System.in);
        System.out.println("--欢迎来到程序检索：");
        System.out.println("--1.返回上一层  2.继续应用程序检索  0.退出  请输入你的选择：");
        int select = input.nextInt();
        if(select>2 || select<0){
            System.out.println("--请重新输入：");
            select = input.nextInt();
        }else if(select==1){
            programDoSelect();
        }else if(select==2){
            programSelect01();
        }else {
            System.exit(1);
        }
    }

    /**
     * 应用程序管理 分支-程序检索
     * */
    public void programSelect01() {
        Scanner input = new Scanner(System.in);
        ProgramServiceImpl psi = new ProgramServiceImpl();
        System.out.println("--请输入你要做的操作：");
        System.out.println("--1.选择程序 2.(关联文件类型 启动应用程序 删除程序) 3.返回上一层");
        int select666 = input.nextInt();

        if(select666==1){
            try {
                //选择跟程序关联的文件，对此文件进行 加密解密打开删除操作
                psi.programOperator();
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        }
        if (select666==2){
            programSelect02();
        }
        if (select666==3){
            programSelect();
        }
    }

    /**
     * 应用程序管理 分支-程序检索-(关联文件类型 启动应用程序 删除程序)
     */
    public void programSelect02() {
        //2.(关联文件类型 启动应用程序 删除程序)
        ProgramServiceImpl psi = new ProgramServiceImpl();
        Scanner input = new Scanner(System.in);
        try {
            psi.showInfo(1);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        System.out.println("________________________________________");
        System.out.println("4.返回上一级 3.关联文件类型 2.启动应用程序 1.删除程序 0.退出");
        System.out.println("请输入你的选择:");
        int select = input.nextInt();
        if(select>4 || select<0){
            System.out.println("未输入43210,请重新输入: ");
            select = input.nextInt();
        }
        programOperator(select);
    }

    /**
     * 应用程序管理 分支-程序检索-选择(关联文件类型 启动应用程序 删除程序) 分支
     * */
    public void programOperator(int select) {

        if (select==3){
            programOperatorAssociateFile();
        }
        if (select==2){
            programOperatorStart();
        }
        if (select==1){
            programOperatorDelete();
        }
        if (select==0){
            System.exit(1);
        }
        if (select==4){
            programSelect01();
        }

    }

    /**
     * 应用程序管理 分支-程序检索-删除程序
     */
    public void programOperatorDelete() {
        ProgramServiceImpl psi = new ProgramServiceImpl();
        Scanner input = new Scanner(System.in);
        System.out.println("--欢迎选择 删除程序 ");
        System.out.println("--1.重新选择 2.继续运行删除程序 0.退出 请输入你的选择：");
        int select = input.nextInt();
        if(select==1){
            programSelect02();
        }else if(select==2){
            System.out.println("--请输入programName:");
            String programName111 = input.next();
            Program program1 = new Program(programName111);
            boolean b1 = false;
            try {
                b1 = psi.deleteProgramReal(program1);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            if(b1){
                System.out.println("程序删除成功！");
                programDoSelect();
            }else{
                System.out.println("程序删除失败！");
                programDoSelect();
            }
            System.out.println("--还要进行其他操作吗？确认输入1，直接退出输入0");
            select = input.nextInt();
            if(select!=0 || select!=1){
                System.out.println("未输入0或1请重新输入：");
                System.out.println("进行其他操作: 确认输入1 直接退出输入0");
                select = input.nextInt();
            }
            if(select==1){
                programSelect02();
            }
            if (select==0){
                System.exit(1);
            }
        }else if(select==0){
            System.exit(1);
        }

    }

    /**
     * 应用程序管理 分支-程序检索-启动程序
     */
    public void programOperatorStart() {
        ProgramServiceImpl psi = new ProgramServiceImpl();
        Scanner input = new Scanner(System.in);
        System.out.println("--欢迎选择  启动程序");
        System.out.println("--1.继续启动程序 2.返回上一级 3.退出系统 请输入你的选择：");
        int select = input.nextInt();
        if(select==1){
            System.out.println("--请输入programName:");
            String programName11 = input.next();
            Program program22 = new Program(programName11);
            Program program2 = null;
            try {
                program2 = psi.startProgramReal(program22);
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
            if(program2!=null){
                System.out.println("启动成功！");
                System.out.println("请输入是否继续操作？ 1.继续操作 2.退出");
                if(input.nextInt()==1){
                    programDoSelect();
                }else {
                    System.exit(1);
                }
            }else {
                System.out.println("启动失败！");
                programDoSelect();
            }
        }

    }

    /**
     * 应用程序管理 分支-程序检索-关联文件类型
     */
    public void programOperatorAssociateFile() {
        ProgramServiceImpl psi = new ProgramServiceImpl();
        Scanner input = new Scanner(System.in);
        try {
            psi.associateFileTypeReal();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



    /**
     * 文件管理 选择一条·分支
     * */
    public void fileDo() {
        Scanner input = new Scanner(System.in);
        System.out.println("欢迎来到文件管理：请输入你的选择：");
        System.out.println("1.载入文件 2.文件检索 3.退出");
        int select = input.nextInt();
        if(select>3 || select<1){
            System.out.println("请重新选择：");
            fileDo();
        }
        if(select==1){
            fileLoad();
        }else if(select==2){
            fileSelect();
        }else if(select==3){
            System.exit(1);
        }

    }

    /**
     * 文件管理 分支-文件加载
     * */
    public void fileLoad() {
        Scanner input = new Scanner(System.in);
        FileServiceImpl fsi = new FileServiceImpl();
        System.out.println("--欢迎选择 载入文件");
        System.out.println("--退出请按0  返回上一级请按1  继续载入文件请按2");
        int select = input.nextInt();
        if(select>2 || select<0){
            System.out.println("请重新选择：");
            fileLoad();
        }
        if(select==0){
            System.exit(1);
        }else if(select==1){
            fileDo();
        }else if(select==2){
            System.out.println("请输入fileName：");
            String fileName1 = input.next();
            System.out.println("请输入fileTypeId(文件类型ID)：");
            int fileTypeId1 = 0;
            try {
                fileTypeId1 = input.nextInt();
            } catch (Exception e) {
                e.printStackTrace();
                fileTypeId1 = 1;
            }
            System.out.println("请输入fileSize：");
            int fileSize1 = input.nextInt();
            System.out.println("请输入fileEncrypt(文件加密状态)：");
            int fileEncrypt1 = 0;
            try {
                fileEncrypt1 = input.nextInt();
            } catch (Exception e) {
                e.printStackTrace();
                fileEncrypt1 = 1;
            }


            String fileEncryptPath1 = null;
            String filePath1 = null;
            if(fileEncrypt1==1){
                System.out.println("请输入fileEncryptPath：");
                fileEncryptPath1 = input.next();
            }else{
                System.out.println("请输入filePath：");
                filePath1 = input.next();
            }

            System.out.println("请输入textcontent：");
            String textcontent1 = input.next();

            File01 file01 = new File01(fileName1,fileTypeId1,fileSize1,fileSize1,fileEncrypt1,filePath1,fileEncryptPath1,textcontent1);
            int i = 0;
            try {
                i = fsi.addFileReal(file01);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            if(i==0) {
                System.out.println("载入文件失败！");
                fileDo();
            }else{
                System.out.println("载入文件成功！");
                fileDo();
            }
        }
    }

    /**
     * 文件管理 分支-文件检索-选择检索方式
     * */
    public void fileSelect() {
        FileServiceImpl fsi = new FileServiceImpl();
        Scanner input = new Scanner(System.in);
        fsi.showTenInfoReal(1);
        System.out.println("欢迎选择文件检索");
        System.out.println("1.文件名检索 2.按内容检索 3.返回上一级 4.退出");
        System.out.println("请输入你的选择:");
        int selectMode = input.nextInt();
        if (selectMode>4 || selectMode<1){
            System.out.println("输入有误，请重新输入：");
            selectMode = input.nextInt();
        }
        if(selectMode == 1){
            fileSelectByName();
            fileDo();
        }
        if (selectMode == 2){
            fileSelectByContent();
            fileDo();
        }
        if (selectMode == 3){
            fileDo();
        }
        if(selectMode==4){
            System.exit(1);
        }
    }

    /**
     * 文件管理 分支-文件检索-按文件名检索
     * */
    public void fileSelectByName() {
        FileServiceImpl fsi = new FileServiceImpl();
        Scanner input = new Scanner(System.in);
        fsi.showTenInfoReal(1);

        fsi.fileOperatorByNameReal();

        fileSelect();

    }

    /**
     * 文件管理 分支-文件检索-按内容检索
     * */
    public void fileSelectByContent() {
        FileServiceImpl fsi = new FileServiceImpl();
        fsi.showTenInfoReal(1);

        fsi.fileOperatorByContentReal();

        fileSelect();
    }

}
