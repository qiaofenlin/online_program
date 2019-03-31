package com.example.online_program.impl;

import com.example.online_program.entity.ProjInfo;
import com.example.online_program.entity.TreeNodeInfo;
import org.apache.ibatis.annotations.Param;

import java.util.Map;


/**
 * @Author: wtt
 * @Date: 19-3-11
 * @Description:
 */
public interface TreeNodeCURD {
    /**
     *  create Project
     * @return
     */
    public boolean createProject(ProjInfo projInfo);

    /**
     * create directory and file
     * @param parentId
     * @return
     */
    public boolean createDireFile(TreeNodeInfo treeNodeInfo);
    /**
     * delete node
     * @return
     */
    public boolean deleteNode(int nodeId);

    /**
     * renameNode
     * @return
     */
    public boolean renameNode(Map<String, Object> params);

}
