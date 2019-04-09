package com.example.online_program;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

/**
 * @Author: wtt
 * @Date: 19-3-12
 * @Description:
 */
public class MyBatisTest {
    public static boolean test1(){
        try {
            if (1==1){
                return true;
            }
        }finally {
            System.out.println("finally!!!");
        }
        return false;
    }
    public static void main(String[] args) {
        /*InputStream in = null;
        SqlSessionFactory factory = null;
        try {
            in = Resources.getResourceAsStream("mybatis-config.xml");
            factory = new SqlSessionFactoryBuilder().build(in);
            SqlSession session = factory.openSession();
            int i=session.insert("insertProj",new ProjInfo());
            System.out.println("影响数据行为："+i);
            session.commit();
        }catch (IOException e){
            e.printStackTrace();
        }*/
        String str = " ";
        System.out.println(str.trim().equals(""));
        System.out.println("--------------"+test1());
    }
}
