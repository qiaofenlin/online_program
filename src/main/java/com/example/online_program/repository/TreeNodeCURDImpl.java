package com.example.online_program.repository;

import com.example.online_program.entity.ProjInfo;
import com.example.online_program.entity.TreeNodeInfo;
import com.example.online_program.impl.TreeNodeCURD;
import com.example.online_program.utils.MybatisUtils;
import com.example.online_program.utils.Utils;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.SqlSession;

import java.util.*;

/**
 * @Author: wtt
 * @Date: 19-3-11
 * @Description:
 */
public class TreeNodeCURDImpl implements TreeNodeCURD {

    private List<List> lists;

    public TreeNodeCURDImpl(List<List> lists) {
        this.lists = lists;
    }

    public TreeNodeCURDImpl() {
    }

    public List<List> getLists() {
        return lists;
    }

    public void setLists(List<List> lists) {
        this.lists = lists;
    }

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


    public boolean renameNode(Map<String, Object> params) {
        SqlSession session = MybatisUtils.getSqlSession();
        Object object = params.get("type");
        String type = null;
        if (object instanceof String) {
            type = (String) object;
        }
        if (type != null && type.trim().equals("proj")) {
            System.out.println("[-------rename proj---------]");
            System.out.println("\n\n================\n"+params.toString());
            session.update("renameProj", params);
            // TODO 修改项目名称
            /**
             * {nodeName=test_qiao111, updateTime=2019-06-03 08:01:26.788, type=proj, nodeId=pd37f613fc514a9bab2dc8d52e60ee1c}
             */

        } else {
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
        MybatisUtils.closeSqlSession(session);
        return list;
    }

    public List<TreeNodeInfo> queryNodeData(String nodeId) {
        List list = null;
        SqlSession session = MybatisUtils.getSqlSession();
        if (session != null && nodeId != null && !nodeId.trim().equals("")) {
            list = session.selectList("queryNode", nodeId);
            System.out.println("[ --- queryNodeData Result : " + list);
        }
        MybatisUtils.closeSqlSession(session);
        return list;
    }

    public String queryProjectName(String projectId) {
        String result = null;
        SqlSession session = MybatisUtils.getSqlSession();
        if (session != null) {
            result = session.selectOne("queryProjName", projectId);
        }
        MybatisUtils.closeSqlSession(session);
        return result;
    }

    @Override
    public void deleteNode(List list) {
        SqlSession session = MybatisUtils.getSqlSession();
        if (session != null&&list.size()>0) {
            int i = session.delete("deleteNode", list);
            session.commit();
            System.out.println("[  deleteNode count is : " + i);
        }
        MybatisUtils.closeSqlSession(session);
    }

    public void deleteProject(String projectId) {
        SqlSession session = MybatisUtils.getSqlSession();
        if (session != null) {
            int i = session.delete("deleteProj", projectId);
            session.commit();
            System.out.println("[  deleteProject count is : " + i);
        }
    }

    public List<List> selectAllChildNodeId(List list) {
        SqlSession session = MybatisUtils.getSqlSession();
        if (session != null&&list.size()>0) {
            List li = session.selectList("queryAllchildNode", list);
            MybatisUtils.closeSqlSession(session);
            System.out.println("[selectAllChildNodeId : ]" + li);
            if (li == null || li.size() < 1) {
                return null;
            }
            lists.add(li);
            selectAllChildNodeId(li);
        }
        return lists;
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
//        TreeNodeCURDImpl tnci = new TreeNodeCURDImpl();
        /*Map map = new HashMap();
        map.put("nodeId","c0c233e827cf482294235d21db79f6dd");
        map.put("nodeName","qweasd123");
        map.put("type","proj");
        map.put("updateTime", Utils.getTimeStamp());
        tnci.renameNode(map);*/

//        tnci.queryProjectList("1111");
        List<List> lists = new ArrayList<>();
        TreeNodeCURDImpl tni1 = new TreeNodeCURDImpl(lists);
        List list = new ArrayList<>();
        list.add("p8cbdd1440c0415a9d944b571a5528dc");
        tni1.selectAllChildNodeId(list);
        System.out.println(Utils.mergeList(lists));
    }
}
