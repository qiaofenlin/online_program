package com.example.online_program.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.online_program.entity.ProjInfo;
import com.example.online_program.entity.TreeNodeInfo;
import com.example.online_program.repository.TreeNodeCURDImpl;
import com.example.online_program.utils.Utils;
import com.example.online_program.utils.result_utils.Result;
import com.example.online_program.utils.result_utils.ResultGenerator;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * @Author: wtt
 * @Date: 19-3-10
 * @Description: treenode curd
 */
@RestController
@RequestMapping("/treenode")
public class TreeNodeCURDController {

    @RequestMapping(value = "/create/proj")
    @ResponseBody
    public Result createProject(@RequestBody JSONObject object) {
        ProjInfo projInfo = new ProjInfo();
        if (object != null && object.containsKey("userId")) {
            projInfo.setProjectId(Utils.getUUIDString("p"));
            //default project name is "Project"
            projInfo.setProjectName("Project");
            projInfo.setUserId(Integer.parseInt(object.getString("userId")));
        }
        TreeNodeCURDImpl tnci = new TreeNodeCURDImpl();
        if (tnci.createProject(projInfo)) {
            return ResultGenerator.genSuccessResult(projInfo);
        }
        return ResultGenerator.genFailResult("create proj is failed !");

    }

    @RequestMapping(value = "/create/dirfile")
    @ResponseBody
    public Result createDirFile(@RequestBody JSONObject object) {
//        System.out.println("[ createDirFile  :  "+object.toJSONString());
        String type = "";
        if (object != null && object.containsKey("type")) {
            type = object.getString("type");
        }
        if (object.containsKey("parentId")) {
            String parentId = object.getString("parentId");
            if (Utils.adjustIsProject(parentId) || Utils.isDirectory(parentId)) {
                TreeNodeInfo tni = new TreeNodeInfo();
                tni.setParentId(parentId);
                tni.setChildId(Utils.getUUIDString("n"));
                if (type.trim().equals("dir")) {
                    //初次创建目录,default="dir"
                    tni.setNodeName("dir");
                    tni.setLabel("0");
                } else {
                    //初次创建文件,default="file"
                    tni.setNodeName("file");
                    tni.setLabel("1");
                }
                if (new TreeNodeCURDImpl().createDireFile(tni)) {
                    return ResultGenerator.genSuccessResult(tni);
                }
            }
        }
        return ResultGenerator.genFailResult("创建位置不正确");
    }

    @PostMapping(value = "/rename")
    @ResponseBody
    public Result renameNode(@RequestBody JSONObject object) {
        String nodeId = object.getString("id");
        String nodeName = object.getString("text");
        String type = object.getString("type");
        System.out.println(nodeId + "\t" + nodeName + "\t" + type);
        Map map = new HashMap();
        map.put("nodeId", nodeId);
        map.put("nodeName", nodeName);
        map.put("type", type);
        map.put("updateTime", Utils.getTimeStamp());
        TreeNodeCURDImpl tnci = new TreeNodeCURDImpl();
        if (tnci.renameNode(map)) {
            return ResultGenerator.genSuccessResult();
        }
        return ResultGenerator.genFailResult("rename node is failed !");
    }

    @PostMapping(value = "/move")
    @ResponseBody
    public Result moveNode(@RequestBody JSONObject object) {
        System.out.println("[----------moveNodeController--------]");
        String sourceId = object.getString("sid");
        String targetId = object.getString("tid");
        String nodeName = object.getString("text");
        System.out.println(sourceId + "\t" + targetId + "\t" + nodeName);
        if (!Utils.isDirectory(targetId)) {
            return ResultGenerator.genFailResult("target should be dir!");
        }
        TreeNodeInfo tni = new TreeNodeInfo();
        tni.setParentId(targetId);
        tni.setChildId(sourceId);
        tni.setNodeName(nodeName);
        if (Utils.isDirectory(sourceId)) {
            tni.setLabel("0");
        } else {
            tni.setLabel("1");
        }
        TreeNodeCURDImpl tnci = new TreeNodeCURDImpl();
        if (tnci.moveNode(tni)) {
            return ResultGenerator.genSuccessResult();
        }
        return ResultGenerator.genFailResult("move failed");
    }

    @PostMapping(value = "/import/list")
    @ResponseBody
    public Result ImportProjectsList(@RequestBody JSONObject object) {
        System.out.println("[-------import ImportProjectsList----]");
        String userId = null;
        if (object != null) {
            userId = object.getString("userId");
        }
        if (userId != null && !userId.trim().equals("")) {
            List list = new TreeNodeCURDImpl()
                    .queryProjectList(userId);
            return ResultGenerator.genSuccessResult(list);
        }
        return ResultGenerator.genFailResult("importList is failed !");
    }

    /**
     * TODO delete treecode includes data
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Result deleteNode(@Param("id") String id) {
        //如果为代码数据将code中数据删除，如果为目录(包括工程)迭代删除
        return ResultGenerator.genSuccessResult();
    }

    /**
     * TODO import project
     *
     * @param object
     * @return
     */
    /*@ResponseBody
    @PostMapping(value = "/getNode")
    public Result getTreeNodes(@RequestBody JSONObject object) {
        System.out.println("[--------into getTreeNodes-------]");
        List li = new ArrayList();
        if (object != null) {
            List<TreeNodeInfo> list = new TreeNodeCURDImpl()
                    .queryNodeData(object.getString("nodeId"));
            for (TreeNodeInfo tni : list) {
                String state = tni.getLabel().equals("0") ? "closed" : "open";
                li.add("{\"id\":\"" + tni.getChildId() + "\",\"text\":\"" + tni.getNodeName() + "\",\"state\":\"" + state + "\"}");
            }
//            System.out.println(li);
        }
        return ResultGenerator.genSuccessResult(li.toString());
    }*/
    @ResponseBody
    @PostMapping(value = "/getNode")
    public String getTreeNodes(String id) {
        System.out.println("[--------into getTreeNodes-------] \t"+id);
        return "[{\"id\":\"1\",\"text\":\"file\",\"state\":\"open\"},\"id\":\"2\",\"text\":\"dir\",\"state\":\"closed\"}]";
    }
}
