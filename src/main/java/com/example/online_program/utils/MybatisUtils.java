package com.example.online_program.utils;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;

/**
 * @Author: wtt
 * @Date: 19-3-29
 * @Description:
 */
public class MybatisUtils {
    private static SqlSessionFactory factory = null;
    static {
        try {
            factory = new SqlSessionFactoryBuilder()
                    .build(Resources.getResourceAsStream("mybatis-config.xml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static SqlSession getSqlSession(){
        return factory.openSession();
    }

    public static void closeSqlSession(SqlSession session){
        session.close();
    }
}
