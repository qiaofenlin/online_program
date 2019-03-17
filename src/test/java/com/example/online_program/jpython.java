package com.example.online_program;

import com.example.online_program.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.python.core.PyFunction;
import org.python.core.PyInteger;
import org.python.core.PyObject;
import org.python.util.PythonInterpreter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;

@RunWith(SpringRunner.class)
@SpringBootTest
public class jpython {
    Logger logger = LoggerFactory.getLogger(UserService.class);


//    public static void main(String[] args) {
//        // TODO Auto-generated method stub
//        PythonInterpreter interpreter = new PythonInterpreter();
//        interpreter.execfile("/home/qiao/java_pojo/online_program/src/test/java/com/example/online_program/add.py");
////        interpreter.execfile("/home/qiao/java_pojo/online_program/src/test/java/yaml_opt.py");
//        System.out.println("*****11");
//        // 第一个参数为期望获得的函数（变量）的名字，第二个参数为期望返回的对象类型
//        PyFunction pyFunction = interpreter.get("add", PyFunction.class);
//        int a = 5, b = 10;
//        //调用函数，如果函数需要参数，在Java中必须先将参数转化为对应的“Python类型”
//        System.out.println("*****22");
//
//        PyObject pyobj = pyFunction.__call__(new PyInteger(a), new PyInteger(b));
////        PyObject pyobj = pyFunction.__call__();
//        System.out.println("*****33");
//
//        System.out.println("the anwser is: " + pyobj);
//    }


//    @Test
//    public String user_get_python() throws IOException {
//        String result = "";
//
//        try {
////            Process process = Runtime.getRuntime().exec("python /home/jia/fireevacuation/my.py " + filename);
//            Process process = Runtime.getRuntime().exec("python /home/qiao/java_pojo/online_program/src/test/java/yaml_opt.py");
////            process.waitFor();
//            InputStreamReader ir = new InputStreamReader(process.getInputStream());
//            LineNumberReader input = new LineNumberReader(ir);
//            result = input.readLine();
//            input.close();
//            ir.close();
////            process.waitFor();
//        } catch (IOException e) {
//            logger.error("调用python脚本并读取结果时出错：" + e.getMessage());
//        }
//        return result;
//
//    }
//


    @Test
    public void user_get_python3() throws IOException {
        Process proc;
        try {
            proc = Runtime.getRuntime().exec("python add.py");// 执行py文件
            //用输入输出流来截取结果
            BufferedReader in = new BufferedReader(new InputStreamReader(proc.getInputStream()));
            String line = null;
            while ((line = in.readLine()) != null) {
                System.out.println(line);
            }
            in.close();
            proc.waitFor();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }



}
