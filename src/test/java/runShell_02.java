import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * 执行本地和远程: https://blog.csdn.net/zgxy666/article/details/83141806
 */
public class runShell_02 {

    public Logger logger = LoggerFactory.getLogger(getClass());
    public List<String> run() throws Exception {

        List<String> stringList = new ArrayList<>();
        //可以执行脚本
        String command = "~/online_program/src/test/java/a.sh";
        //可以执行命令
//        String command = "ps -ef|grep java";
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

    private int execShell(String shellPath, String... params) {
        StringBuilder command = new StringBuilder(shellPath).append(" ");
        for (String param : params) {
            command.append(param).append(" ");
        }

        BufferedReader br = null;
        StringBuilder sb = null;
        try {
            Process process = Runtime.getRuntime().exec(command.toString());
            process.waitFor();

            br = new BufferedReader(new InputStreamReader(process.getInputStream()));
            sb = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line);
                logger.debug(line);
            }
        } catch (Exception e) {
            logger.error("execShell() error, shellPath: {}, params: {}", shellPath, params, e);
            return -2;
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        logger.debug("222 "+sb.toString());
        return "".equals(sb.toString()) ? 0 : Integer.parseInt(sb.toString());
    }
    public static void main(String[] args) throws Exception {
        runShell_02 r = new runShell_02();
//        r.run();
//        r.execShell("python /home/qiao/java_pojo/online_program/src/test/java/yaml_opt.py");
        r.execShell("python /home/qiao/aidweb/runserver.py");
//        r.execShell(". ~/online_program/src/test/java/a.sh");
    }
}
