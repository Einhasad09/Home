package com.jr.util;

import com.jr.entity.File01;
import com.jr.test.Login;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Scanner;

public class FileOperator {

    /**
     * selectedFile:选择的文件名
     * selectedOperator:选择的操作
     * 1.文件加密2.文件解密3.打开文件4.删除文件
     * */
    private BaseDao baseDao = new BaseDao();

    /**
     * 1.文件加密  0加密  1解密
     * */
    public void encryptedReal(int selectedFile) throws InvocationTargetException, SQLException, InstantiationException, IllegalAccessException, NoSuchMethodException {
        File01 file011 = new File01();
        Login login = new Login();
        String sql = "select * from db_file where fileid=?";
        List list = baseDao.queryInfo02(file011.getClass(), sql, selectedFile);
        String[] str = list.get(0).toString().split(",");
        System.out.println(str[4]);
        if (list.isEmpty()) {
            System.out.println("文件不存在");;
        }else if(str[4].contains("fileEncrypt='1'")){
            String updatesql = "update db_file set fileencrypt=?,filepath=?,fileencryptpath=? where fileid=?";
            Object[] objs = {0,null,"F:\\Znaconda",selectedFile};
            baseDao.updateInfo(updatesql,objs);
            System.out.println("加密成功！");
            login.loginDo021();

        }else{
            System.out.println("文件已经加密！无需再次加密！");
            login.loginDo021();
        }
    }

    /**
     * 2.文件解密 需要fileencrypt为0的文件解密  0加密  1解密
     */
    public void decryptReal(int selectedFile) throws InvocationTargetException, SQLException, InstantiationException, IllegalAccessException, NoSuchMethodException {
        Scanner input = new Scanner(System.in);
        Login login = new Login();
        File01 file011 = new File01();
        String sql = "select * from db_file where fileid=?";
        List list = baseDao.queryInfo02(file011.getClass(), sql, selectedFile);
        String[] str = list.get(0).toString().split(",");
        if(list.isEmpty()){
            System.out.println("文件不存在");
            login.loginDo021();
        }else if(str[4].contains(" fileEncrypt='1'")){
            System.out.println("文件无需解密！");
            login.loginDo021();
        }else{
            System.out.println("请输入要将解密的文件放在哪个路径(输入路径): ");
            String filePath = input.next();
            String updatesql = "update db_file set fileencrypt=?,filepath=?,fileencryptpath=? where fileid=?";
            Object[] objs = {1,filePath,null,selectedFile};
            baseDao.updateInfo(updatesql,objs);
            System.out.println("解密成功！");
            login.loginDo021();
        }

    }

    /**
     * 3.打开文件
    * filetypeid
    * 通过filetypeid查找proid和filename,如果查找到了一个proid那么
    * 那么通过proid查找proname；
    * 用System.out.println("文件：" + filename + "，只匹配到一个程序，将用" + proname + "打开!");格式输出
    * 如果查找到了两个以及以上proid那么
    * 输出两个proid对应的记录，然后用户手动输入proid选择一条记录
    * 选择记录之后通过proid查找proname
    * 用System.out.println("文件：" + filename + "，只匹配到一个程序，将用" + proname + "打开!");格式输出
    * 如果没查找到proid那么用System.out.println("文件：" + name + "，没有匹配到对应的打开程序，将直接打开");格式输出
    * */
    public void openFileReal(int selectedFile) throws SQLException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        // 根据selectedFile (fileid) 查询filetypeid
        Login login = new Login();
        BaseDao baseDao = new BaseDao();
        String sqlFileType = "SELECT filetypeid, filename FROM db_file WHERE fileid = " + selectedFile;
        List<LinkedHashMap<String, Object>> resultFileType = baseDao.queryInfo01(sqlFileType);

        if (resultFileType.isEmpty()) {
            System.out.println("未找到相应的文件类型。");
            login.loginDo021();
        }

        String fileTypeId = resultFileType.get(0).get("filetypeid").toString();
        String filename = resultFileType.get(0).get("filename").toString();

        // 通过filetypeid查找db_program的proid
        String sql = "select p.proid " +
                "from db_filetype ft " +
                "join db_progandfiletype db on ft.filetypeid = db.filetypeprimarykey " +
                "join db_program p ON db.programprimarykey = p.proid " +
                "WHERE ft.filetypeid = " + fileTypeId;
        List<LinkedHashMap<String, Object>> results = baseDao.queryInfo01(sql);

        if (results.isEmpty()) {
            // 没查找到proid
            System.out.println("文件：" + filename + "，没有匹配到对应的打开程序，将直接打开");
            String sql77 = "select * from db_file where fileid=?";
            List<File01> list = baseDao.queryInfo(File01.class,sql77,selectedFile);
            System.out.println(list);
            login.loginDo021();
        }

        if (results.size() == 1) {
            String proId = results.get(0).get("proid").toString();

            // 通过proid查找proname
            String sqlProName = "select proname from db_program WHERE proid = " + proId;
            String proname = baseDao.queryInfo01(sqlProName).get(0).get("proname").toString();

            System.out.println("文件：" + filename + "，只匹配到一个程序，将用" + proname + "打开!");
            //openFileReal(selectedFile);
            login.loginDo021();
        }

        if (results.size() > 1) {

                System.out.println("找到多个程序可以打开该文件，请选择一个：");
                for (LinkedHashMap<String, Object> result : results) {
                    String proId = result.get("proid").toString();
                    System.out.println(proId);
                }

                System.out.print("输入你选择的proid(或输入'退出'结束):");
                Scanner scanner = new Scanner(System.in);
                String selectedProId = scanner.nextLine();
                if (selectedProId.trim().isEmpty()) {
                    login.loginDo021();
                }
                if ("退出".equals(selectedProId)) {
                    login.loginDo021();
                }

                // 选择记录之后通过proid查找proname
                String sqlProName = "select proname from db_program where proid = " + selectedProId;
                String selectedProName = baseDao.queryInfo01(sqlProName).get(0).get("proname").toString();

                System.out.println("文件：" + filename + "，将用" + selectedProName + "打开!");
                System.out.println();
                //openFileReal(selectedFile);
            login.loginDo021();
        }
    }

    /**
     * 4.删除文件
     * */
    public void deleteReal(int selectedFile) throws SQLException {
        File01 file011 = new File01();
        Login login = new Login();
        Scanner input = new Scanner(System.in);
        String sql = "delete from db_file where fileid=?";
        System.out.println("请问真的要删除吗？");
        System.out.println("1.删除  2.不删除");
        int select = input.nextInt();
        if(select!=1&&select!=0){
            System.out.println("请重新输入：");
            select = input.nextInt();
        }
        if(select==1){
            int i = baseDao.updateInfo(sql, selectedFile);
            if(i==0){
                System.out.println("删除失败！");
            }else{
                System.out.println("删除成功！");
            }
            login.loginDo021();
        }else if(select==2){
            login.fileSelect();
        }

    }





    /**
     * 按内容
     * 1.文件加密2.文件解密3.打开文件4.删除文件
     * */

    /**
     * 1.文件加密  0加密  1解密
     * */
    public void encryptedContentReal(int selectedFile)  {
        File01 file011 = new File01();
        Login login = new Login();
        String sql = "select * from db_file where fileid=?";
        List list = null;
        try {
            list = baseDao.queryInfo02(file011.getClass(), sql, selectedFile);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
        String[] str = list.get(0).toString().split(",");
        System.out.println(str[4]);
        if (list.isEmpty()) {
            System.out.println("文件不存在");
        }else if(str[4].equals(" fileEncrypt='1'")){
            String updatesql = "update db_file set fileencrypt=?,fileencryptpath=? where fileid=?";
            Object[] objs = {0,"F:\\Znaconda",selectedFile};
            try {
                baseDao.updateInfo(updatesql,objs);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            System.out.println("加密成功！");
        }else{
            System.out.println("文件已经加密！无需再次加密！");
        }
            login.loginDo021();


    }

    /**
     * 2.文件解密 需要fileencrypt为0的文件解密  0加密  1解密
     */
    public void decryptContentReal(int selectedFile)  {
        Scanner input = new Scanner(System.in);
        File01 file011 = new File01();
        Login login = new Login();
        String sql = "select * from db_file where fileid=?";
        List list = null;
        try {
            list = baseDao.queryInfo02(file011.getClass(), sql, selectedFile);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
        String[] str = list.get(0).toString().split(",");
        if(list.isEmpty()){
            System.out.println("文件不存在");
        }else if(str[4].contains(" fileEncrypt='1'")){
            System.out.println("文件无需解密！");
        }else{
            System.out.println("请输入要将解密的文件放在哪个路径(输入路径): ");
            String filePath = input.next();
            String updatesql = "update db_file set fileencrypt=?,filepath=?,fileencryptpath=? where fileid=?";
            Object[] objs = {1,filePath,null,selectedFile};
            try {
                baseDao.updateInfo(updatesql,objs);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            System.out.println("解密成功！");
        }
            login.loginDo021();

    }

    /**
     * 3.打开文件
     */
    public void openContentReal(int selectedFile) throws SQLException {
        // 根据selectedFile (fileid) 查询filetypeid
        Login login = new Login();
        String sqlFileType = "SELECT filetypeid, filename FROM db_file WHERE fileid = " + selectedFile;
        List<LinkedHashMap<String, Object>> resultFileType = baseDao.queryInfo01(sqlFileType);

        if (resultFileType.isEmpty()) {
            System.out.println("未找到相应的文件类型。");
            login.loginDo021();
        }

        String fileTypeId = resultFileType.get(0).get("filetypeid").toString();
        String filename = resultFileType.get(0).get("filename").toString();

        // 通过filetypeid查找db_program的proid
        String sql = "SELECT p.proid " +
                "FROM db_filetype ft " +
                "JOIN db_progandfiletype db ON ft.filetypeid = db.filetypeprimarykey " +
                "JOIN db_program p ON db.programprimarykey = p.proid " +
                "WHERE ft.filetypeid = " + fileTypeId;
        List<LinkedHashMap<String, Object>> results = baseDao.queryInfo01(sql);

        if (results.isEmpty()) {
            // 没查找到proid
            System.out.println("文件：" + filename + "，没有匹配到对应的打开程序，将直接打开");
            login.loginDo021();
        }

        if (results.size() == 1) {
            String proId = results.get(0).get("proid").toString();

            // 通过proid查找proname
            String sqlProName = "SELECT proname FROM db_program WHERE proid = " + proId;
            String proname = baseDao.queryInfo01(sqlProName).get(0).get("proname").toString();

            System.out.println("文件：" + filename + "，只匹配到一个程序，将用" + proname + "打开!");
            //openContentReal(selectedFile);
            login.loginDo021();
        }

        if (results.size() > 1) {

            System.out.println("找到多个程序可以打开该文件，请选择一个：");
            for (LinkedHashMap<String, Object> result : results) {
                String proId = result.get("proid").toString();
                System.out.println(proId);
            }

            System.out.print("输入你选择的proid(或输入'退出'结束):");
            Scanner scanner = new Scanner(System.in);
            String selectedProId = scanner.nextLine();
            if (selectedProId.trim().isEmpty()) {
                login.loginDo021();
            }
            if ("退出".equals(selectedProId)) {
                login.loginDo021();
            }

            // 选择记录之后通过proid查找proname
            String sqlProName = "SELECT proname FROM db_program WHERE proid = " + selectedProId;
            String selectedProName = baseDao.queryInfo01(sqlProName).get(0).get("proname").toString();

            System.out.println("文件：" + filename + "，将用" + selectedProName + "打开!");
            System.out.println();
            //openContentReal(selectedFile);
        }
            login.loginDo021();
    }

    /**
     * 4.删除文件
     */
    public void deleteContentReal(int selectedFile) throws SQLException {
        File01 file011 = new File01();
        Login login = new Login();
        Scanner input = new Scanner(System.in);
        String sql = "delete from db_file where fileid=?";
        System.out.println("请问真的要删除吗？");
        System.out.println("1.删除  2.不删除");
        int select = input.nextInt();
        if(select!=1&&select!=0){
            System.out.println("请重新输入：");
            select = input.nextInt();
        }
        if(select==1){
            int i = baseDao.updateInfo(sql, selectedFile);
             if(i==0){
                System.out.println("删除失败！");
                 login.loginDo021();
            }else{
                System.out.println("删除成功！");
                 login.loginDo021();
            }
        }else if(select==0){

                login.loginDo021();
        }
    }
}
