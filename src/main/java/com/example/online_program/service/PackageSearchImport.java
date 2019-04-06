package com.example.online_program.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * @Author: wtt
 * @Date: 19-4-6
 * @Description:
 */
public class PackageSearchImport {
    public static void searchPackage(){

    }
    public static void importPackage(){

    }

    public static void main(String[] args) throws IOException, InterruptedException {
//        String[] cmds = {"/bin/sh","-c","ps -ef|grep java"};
//        String[] cmds = {"/bin/sh","-c","pip search pika"};
//        String[] cmds = {"pwd"};
        String[] cmds = {"/bin/sh","-c","pip3 install pika"};
        Process pro = Runtime.getRuntime().exec(cmds);
        pro.waitFor();
        InputStream in = pro.getInputStream();
        BufferedReader read = new BufferedReader(new InputStreamReader(in));
        String line = null;
        while((line = read.readLine())!=null){
            System.out.println(line);
        }
    }
}
