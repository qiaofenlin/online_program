package com.example.online_program;

import com.example.online_program.entity.ProjInfo;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

/**
 * @Author: wtt
 * @Date: 19-3-12
 * @Description:
 */
public class MyBatisTest {
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
    }
}
