package com.example.online_program.utils;
import org.apache.ibatis.session.SqlSession;

import java.sql.Timestamp;
import java.util.UUID;

/**
 * @Author: wtt
 * @Date: 19-3-29
 * @Description:
 */
public class Utils {
    public static String getUUIDString(){
        return UUID.randomUUID().toString().replaceAll("-","");
    }
    public static String getTimeStamp(){
        return new Timestamp(System.currentTimeMillis()).toString();
    }

    /**
     * @param replacement
     * n 为普通节点
     * p 为工程节点
     * @return
     */
    public static String getUUIDString(String replacement){
        return new StringBuffer(getUUIDString())
                .replace(0,1,replacement)
                .toString();
    }

    /**
     * adjust node is Project or not by nodeId
     * @param id
     * @return
     */
    public static boolean adjustIsProject(String id){
        if (id==null||id.trim().equals("")){
            return false;
        }
        if (id.trim().substring(0,1).equals("p")){
            return true;
        }
        return false;
    }

    public static boolean isDirectory(String parentId){
        SqlSession session = MybatisUtils.getSqlSession();
        if (session!=null){
            String label = session.selectOne("selectLabel",parentId);
            if (label.equals("0")){
                return true;
            }
        }
        return false;

    }
    public static void main(String[] args) {
        for (int i = 0;i<10;i++){
            System.out.println(new StringBuffer(getUUIDString()).replace(0,1,"p"));
        }
        System.out.println(getTimeStamp());
    }
}
