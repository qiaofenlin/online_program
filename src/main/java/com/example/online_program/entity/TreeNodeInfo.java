package com.example.online_program.entity;

/**
 * @Author: wtt
 * @Date: 19-3-10
 * @Description:
 */
public class TreeNodeInfo {
    //父节点ID
    private String parentId;
    //子节点ID
    private String childId;
    //节点名称
    private String nodeName;
    //目录,文件(0为目录,1为文件)
    private String label;

    public TreeNodeInfo(){}

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getChildId() {
        return childId;
    }

    public void setChildId(String childId) {
        this.childId = childId;
    }

    public String getNodeName() {
        return nodeName;
    }

    public void setNodeName(String nodeName) {
        this.nodeName = nodeName;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public TreeNodeInfo(String parentId, String childId, String nodeName, String label) {
        this.parentId = parentId;
        this.childId = childId;
        this.nodeName = nodeName;
        this.label = label;
    }
}
