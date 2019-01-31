package com.example.online_program.utils;

/**
 * @Created by qfl
 * @Date 19-1-31
 * @Class: FolderTranferJson.java
 * @package_name: online_program
 * @Description: 将文件夹转化成json格式。
 */

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;

import java.io.File;
import java.util.HashMap;

public class FolderTranferJson {
    public static void main(String[] args) {
        String static_root = "/home/qiao/java_poj/online_program";
        String json = dir2json(static_root);
        System.out.println(json);
    }

    /*TODO 调用该方法返回文件树*/
    public static String dir2json(String dir_path) {
        HashMap<String, Object> dirMap = new HashMap<String, Object>();
        File root = new File(dir_path);
        dir2map(root, dirMap);
        JSONObject mapper = new JSONObject();

        String json = JSONObject.toJSONString(dirMap.get(root.getName()), SerializerFeature.WriteMapNullValue);
        System.out.println(json);
        return json;
    }

    public static boolean shouldSkip(String filename) {
        return filename.startsWith(".");
    }

    /**
     * @param node   文件节点
     * @param dirMap 表示文件所在目录的map
     */
    public static void dir2map(File node, HashMap<String, Object> dirMap) {
        //跳过隐藏文件等
        if (shouldSkip(node.getName())) {
            return;
        }
        //是文件，保存文件名和最后修改时间戳
        if (node.isFile()) {
            dirMap.put(node.getName(), node.lastModified());
        }
        //是目录，建立下一层map，并填充
        if (node.isDirectory()) {
            HashMap<String, Object> subDir = new HashMap<String, Object>();
            dirMap.put(node.getName(), subDir);
            for (String filename : node.list()) {
                dir2map(new File(node, filename), subDir);//填充
            }
        }

    }


}
