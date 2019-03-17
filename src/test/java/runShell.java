import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * 执行本地和远程: https://blog.csdn.net/zgxy666/article/details/83141806
 */
public class runShell {

    public Logger logger = LoggerFactory.getLogger(getClass());
    public List<String> run() throws Exception {

        List<String> stringList = new ArrayList<>();
        //可以执行脚本
//        String command = "~/online_program/src/test/java/a.sh";
        //可以执行命令
        String command = "python /home/qiao/java_pojo/online_program/src/test/java/yaml_opt.py";
        //可以执行带参数的脚本
//        String[] command = {"/usr/local/RPFiles/transStr.sh", "test"};
        Process ps = Runtime.getRuntime().exec(command);
        int exitValue = ps.waitFor();
        //当返回值为0时表示执行成功
        if (0 != exitValue)
            logger.info("call shell failed. error code is :" + exitValue);
        //只能接收脚本echo打印的数据，并且是echo打印的最后一次数据，如果想打印所有数据，可以参考本篇文章的脚本编写
        BufferedInputStream in = new BufferedInputStream(ps.getInputStream());
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        String line;
        while ((line = br.readLine()) != null) {
            logger.info("脚本返回的数据如下： " + line);
            stringList.add(line);
        }
        in.close();
        br.close();
        return stringList;
    }

    public static void main(String[] args) throws Exception {
        runShell r = new runShell();
        r.run();
    }

}
