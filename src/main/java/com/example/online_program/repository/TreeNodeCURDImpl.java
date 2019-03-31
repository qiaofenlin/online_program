package com.example.online_program.repository;

import com.example.online_program.entity.ProjInfo;
import com.example.online_program.entity.TreeNodeInfo;
import com.example.online_program.impl.TreeNodeCURD;
import com.example.online_program.utils.MybatisUtils;
import com.example.online_program.utils.Utils;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.SqlSession;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: wtt
 * @Date: 19-3-11
 * @Description:
 */
public class TreeNodeCURDImpl implements TreeNodeCURD {


    /**
     * createProject
     *
     * @param projInfo
     * @return
     */
    @Override
    public boolean createProject(ProjInfo projInfo) {
        SqlSession session = MybatisUtils.getSqlSession();
        int i = session.insert("insertProj", projInfo);
        System.out.println("[--- createProject --- 影响数据行 i 为：" + i);
        /*TreeNodeInfo tni = new TreeNodeInfo();
        tni.setParentId(projInfo.getProjectId());
        tni.setNodeName(projInfo.getProjectName());
        tni.setLabel("0");
        int j=session.insert("insertNode",tni);
        System.out.println("影响数据行 j 为："+j);*/
        session.commit();
        MybatisUtils.closeSqlSession(session);
        return true;
    }

    /**
     * createDireFile
     *
     * @param treeNodeInfo
     * @return
     */
    @Override
    public boolean createDireFile(TreeNodeInfo treeNodeInfo) {
        SqlSession session = MybatisUtils.getSqlSession();
        /*int i = session.update("updateNode",treeNodeInfo);
        System.out.println("影响数据行 i 为："+i);
        treeNodeInfo.setParentId(treeNodeInfo.getChildId());*/
        int j = session.insert("insertNode", treeNodeInfo);
        System.out.println("影响数据行 j 为：" + j);
        session.commit();
        MybatisUtils.closeSqlSession(session);
        return true;
    }

    @Override
    public boolean deleteNode(int nodeId) {
        return false;
    }

    public boolean renameNode(Map<String, Object> params) {
        SqlSession session = MybatisUtils.getSqlSession();
        Object object = params.get("type");
        String type = null;
        if (object instanceof String) {
            type = (String) object;
        }
        if (type != null && type.trim().equals("proj")) {
            System.out.println("[-------rename proj---------]");
            session.update("renameProj", params);
        }else {
            System.out.println("[-------rename node---------]");
            session.update("renameNode", params);
        }
        session.commit();
        MybatisUtils.closeSqlSession(session);
        return true;
    }

    public List<ProjInfo> queryProjectList(String userId) {
        List<ProjInfo> list = null;
        SqlSession session = MybatisUtils.getSqlSession();
        if (session != null) {
            list = session.selectList("queryProjList", userId);
            System.out.println(list);
            for (ProjInfo info : list) {
                System.out.println(info.getProjectId() + "\t" + info.getProjectName());
            }
        }
        return list;
    }
    public List<TreeNodeInfo> queryNodeData(String nodeId) {
        List list = null;
        SqlSession session = MybatisUtils.getSqlSession();
        if (session!=null&&nodeId!=null&&!nodeId.trim().equals("")){
            list = session.selectList("queryNode",nodeId);
            System.out.println("[ --- queryNodeData Result : "+list);
        }
        return list;
    }

    public boolean moveNode(TreeNodeInfo treeNodeInfo) {
        SqlSession session = MybatisUtils.getSqlSession();
        session.delete("deleteRelative", treeNodeInfo);
        session.insert("insertNode", treeNodeInfo);
        session.commit();
        MybatisUtils.closeSqlSession(session);
        return true;
    }

    public static void main(String[] args) {
//        isDirectory("163e2a632ca5478482fbfaa6783f49c5");
        TreeNodeCURDImpl tnci = new TreeNodeCURDImpl();
        /*Map map = new HashMap();
        map.put("nodeId","c0c233e827cf482294235d21db79f6dd");
        map.put("nodeName","qweasd123");
        map.put("type","proj");
        map.put("updateTime", Utils.getTimeStamp());
        tnci.renameNode(map);*/

        tnci.queryProjectList("1111");

    }
}
