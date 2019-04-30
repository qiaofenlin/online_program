package com.example.online_program.controller;

import com.example.online_program.utils.page_utils.PageBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: online_program
 * @description: 所有控制器的基类
 * @author: qfl
 * @create: 2019-04-29 10:10
 */

@RestController
public class BaseController {

    private Logger logger = LoggerFactory.getLogger(UserController.class);
    private PageBean pageBean = new PageBean();

    public Logger getLogger() {
        return logger;
    }

    public void setLogger(Logger logger) {
        this.logger = logger;
    }

    public PageBean getPageBean() {
        return pageBean;
    }

    public void setPageBean(PageBean pageBean) {
        this.pageBean = pageBean;
    }
}
