//package com.example.online_program.netty_component;
//
//
//import java.io.IOException;
//import java.io.OutputStream;
//import java.io.PrintWriter;
//import java.net.Socket;
//
///**
// * @Author: qfl
// * @Date: 19-1-5 下午5:34
// * @Description:
// */
//public class test_demo {
//    public static void main(String[] args){
//        try {
//            Socket socket=new Socket("localhost",8080);
//            OutputStream outputStream = socket.getOutputStream();
//            PrintWriter printWriter=new PrintWriter(outputStream);
//            printWriter.write("$tmb00035ET3318/08/22 11:5804029.94,027.25,20.00,20.00$");
//            printWriter.flush();
//            socket.shutdownOutput();
//            socket.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//}
