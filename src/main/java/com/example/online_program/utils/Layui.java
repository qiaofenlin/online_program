package com.example.online_program.utils;

import java.util.HashMap;
import java.util.List;

/**
 * @Created by qfl
 * @Date: 19-2-26
 * @package_name: com.example.online_program.utils
 * @Description:
 */
public class Layui  extends HashMap<String, Object> {

    public static Layui data(Integer count, List<?> data){
        Layui r = new Layui();
        r.put("code", 0);
        r.put("msg", "");
        r.put("count", count);
        r.put("data", data);
        return r;
    }
}
