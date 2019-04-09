package com.example.online_program.controller;

import com.example.online_program.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Date;

/**
 * @Author: wtt
 * @Date: 19-4-4
 * @Description:
 */
@RestController
@RequestMapping("/page")
public class FileStreamController {
    @Autowired
    private HttpServletRequest request;
    @Autowired
    private HttpServletResponse response;

    @RequestMapping(value = "/export", method = RequestMethod.GET)
    public ResponseEntity<FileSystemResource> getSearchResult(String path) {
        System.out.println("path : " + path);
//        String filePath = "/home/wtt/下载/北京地大2019年硕士研究生招生目录.xls";
        String filePath = "/home/wtt/下载/2019年硕士研究生招生简章.pdf";
//        String filePath = "/home/wtt/IdeaProjects/online_program/src/main/resources/static/editCode.html";
        File file = new File(filePath);
        HttpHeaders headers = new HttpHeaders();
//        headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
//        headers.add("Content-Disposition", "attachment; filename=" + System.currentTimeMillis() + ".pdf");
//        headers.add("Pragma", "no-cache");
//        headers.add("Expires", "0");
//        headers.add("Last-Modified", new Date().toString());
//        headers.add("ETag", String.valueOf(System.currentTimeMillis()));
        return ResponseEntity
                .ok()
                .headers(headers)
                .contentLength(file.length())
                .contentType(MediaType.parseMediaType("application/octet-stream"))
//                .contentType(MediaType.TEXT_HTML)
                .body(new FileSystemResource(file));
    }

    @RequestMapping(value = "/test")
    public void getHtmlStream(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Utils.disRequestData(request);
        byte[] bytes = htm.getBytes();
        OutputStream out = response.getOutputStream();
        response.setHeader("Content-Type", "text/html");
        response.setIntHeader("Content-Length", bytes.length);
        out.write(bytes);
        out.flush();
        out.close();
    }

    public static String htm = "<!DOCTYPE html>\n" +
            "<html>\n" +
            "<head>\n" +
            "    <meta charset=\"UTF-8\">\n" +
            "    <title>edit code</title>\n" +
            "    <script type=\"text/javascript\" src=\"https://www.jeasyui.com/easyui/jquery.min.js\"></script>\n" +
            "    <script type=\"text/javascript\" src=\"https://www.jeasyui.com/easyui/jquery.easyui.min.js\"></script>\n" +
            "    <link rel=\"stylesheet\" type=\"text/css\" href=\"https://www.jeasyui.com/easyui/themes/default/easyui.css\">\n" +
            "    <link rel=\"stylesheet\" type=\"text/css\" href=\"https://www.jeasyui.com/easyui/themes/icon.css\">\n" +
            "    <link rel=\"stylesheet\" type=\"text/css\" href=\"https://www.jeasyui.com/easyui/themes/icon.css\">\n" +
            "    <!--自定义js,css文件-->\n" +
            "    <script type=\"application/javascript\" src=\"http://127.0.0.1:8083/js/edit.js\"></script>\n" +
            "    <link rel=\"stylesheet\" type=\"text/css\" href=\"http://127.0.0.1:8083/css/edit.css\">\n" +
            "</head>\n" +
            "<body class=\"easyui-layout\">\n" +
            "        <div id=\"north\" data-options=\"region:'north'\" title=\"在线代码编辑运行平台\" style=\"height: 70px\">\n" +
            "            <a href=\"#\" class=\"easyui-menubutton\" data-options=\"menu:'#mm1'\">File</a>\n" +
            "            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;\n" +
            "            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;\n" +
            "            <input type=\"button\" value=\"Run\" id=\"runbtn\">&nbsp;&nbsp;\n" +
            "            <input type=\"button\" value=\"Save\" id=\"savebtn\">&nbsp;&nbsp;\n" +
            "            <input type=\"button\" value=\"MyCode\" id=\"codebtn\">&nbsp;&nbsp;\n" +
            "        </div>\n" +
            "        <div data-options=\"region:'south',split:true\" title=\"Console\" id=\"south\">\n" +
            "            <textarea title=\"run result\" id=\"consoleText\">\n" +
            "\n" +
            "            </textarea>\n" +
            "        </div>\n" +
            "        <div id=\"west\" data-options=\"region:'west',split:true\" title=\"Project\" style=\"width:150px;\">\n" +
            "            <ul id=\"tt\" class=\"easyui-tree\">\n" +
            "            </ul>\n" +
            "        </div>\n" +
            "        <div data-options=\"region:'center',title:'Edit Your Code',iconCls:'icon-ok'\" id=\"center\">\n" +
            "            <textarea title=\"code edit\" autofocus=\"autofocus\" required=\"required\" id=\"runText\">print(\"hello world !\")</textarea>\n" +
            "        </div>\n" +
            "        <!--以下为菜单按钮密码mm1-->\n" +
            "        <div id=\"mm1\" style=\"width:150px;\">\n" +
            "            <div>\n" +
            "                <span>New</span>\n" +
            "                <div>\n" +
            "                    <div onclick=\"createProject()\">Project</div>\n" +
            "                    <div onclick=\"createDirFile('dir')\">Directory</div>\n" +
            "                    <div onclick=\"createDirFile()\">File</div>\n" +
            "                </div>\n" +
            "            </div>\n" +
            "            <div data-options=\"iconCls:'icon-remove',name:'delete'\" onclick=\"deleteNode()\">Delete</div>\n" +
            "            <!--<div onclick=\"importProject()\">Import Project</div>-->\n" +
            "            <div>\n" +
            "                <span id=\"importProj\">ImportMyProject</span>\n" +
            "                <div id=\"myProjsList\">\n" +
            "\n" +
            "                </div>\n" +
            "            </div>\n" +
            "        </div>\n" +
            "\n" +
            "</body>\n" +
            "</html>";
}
