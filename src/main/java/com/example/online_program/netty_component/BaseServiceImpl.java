package com.example.online_program.netty_component;

import org.springframework.stereotype.Service;

/**
 * @Author: qfl
 * @Date: 19-1-5 下午5:27
 * @Description:
 */
@Service
public class BaseServiceImpl implements BaseService {
    @Override
    public void test() {
        System.out.println("调用service服务");
    }
}
