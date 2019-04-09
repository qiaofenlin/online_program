package com.example.online_program.entity;

/**
 * @Author: wtt
 * @Date: 19-3-11
 * @Description:
 */
public class ProjInfo {
    //工程ID
    private String projectId;
    //工程名称
    private String projectName;
    //用户ID
    private int userId;
    //工程创建时间
    private String createTime;
    //工程修改时间
    private String updateTime;
    public ProjInfo(){

    }

    public ProjInfo(String projectId, String projectName, int userId, String createTime, String updateTime) {
        this.projectId = projectId;
        this.projectName = projectName;
        this.userId = userId;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public static void main(String[] args) {
        System.out.println(new ProjInfo("111","xxx",111,"123","456").toString());
    }
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("\"projectId\":\"")
                .append(projectId).append('\"');
        sb.append(",\"projectName\":\"")
                .append(projectName).append('\"');
        sb.append(",\"userId\":")
                .append(userId);
        sb.append(",\"createTime\":\"")
                .append(createTime).append('\"');
        sb.append(",\"updateTime\":\"")
                .append(updateTime).append('\"');
        sb.append('}');
        return sb.toString();
    }
}
