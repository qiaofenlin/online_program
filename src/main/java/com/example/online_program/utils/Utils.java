package com.example.online_program.utils;

import org.apache.ibatis.session.SqlSession;

import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @Author: wtt
 * @Date: 19-3-29
 * @Description:
 */
public class Utils {
    public static String getUUIDString() {
        return UUID
                .randomUUID()
                .toString()
                .replaceAll("-", "");
    }

    public static String getTimeStamp() {
        return new Timestamp(
                System.currentTimeMillis()
        ).toString();
    }

    /**
     * @param replacement n 为普通节点
     *                    p 为工程节点
     * @return
     */
    public static String getUUIDString(String replacement) {
        return new StringBuffer(getUUIDString())
                .replace(0, 1, replacement)
                .toString();
    }

    /**
     * adjust node is Project or not by nodeId
     *
     * @param id
     * @return
     */
    public static boolean adjustIsProject(String id) {
        if (id == null || id.trim().equals("")) {
            return false;
        }
        if (id.trim().substring(0, 1).equals("p")) {
            return true;
        }
        return false;
    }

    /**
     * 0为目录文件,1为普通文件
     *
     * @param parentId
     * @return
     */
    public static boolean isDirectory(String parentId) {
        SqlSession session = MybatisUtils.getSqlSession();
        if (session != null) {
            String label = session.selectOne("selectLabel", parentId);
            if (label != null && label.equals("0")) {
                return true;
            }
            MybatisUtils.closeSqlSession(session);
        }
        return false;

    }

    /**
     * merge many inner list of list to a list
     *
     * @param lists
     * @return
     */
    public static List mergeList(List<List> lists) {
        List list = new ArrayList();
        if (lists != null && lists.size() > 0) {
            for (List li : lists) {
                list.addAll(li);
            }
            return list;
        }
        return list;
    }

    public static void disRequestData(HttpServletRequest request) {
        System.out.println("req uri : " + request.getRequestURI());
        System.out.println("req url : " + request.getRequestURL());
        System.out.println("req ContextPath" + request.getContextPath());
        System.out.println("req ServletPath : " + request.getServletPath());
        System.out.println("req getQueryString : " + request.getQueryString());
    }

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            System.out.println(new StringBuffer(getUUIDString()).replace(0, 1, "p"));
        }
        System.out.println(getTimeStamp());
    }
}
