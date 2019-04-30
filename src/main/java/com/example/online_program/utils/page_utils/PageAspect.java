//package com.example.online_program.utils.page_utils;
//
//import com.github.pagehelper.PageHelper;
//import com.github.pagehelper.PageInfo;
//import org.aspectj.lang.ProceedingJoinPoint;
//import org.aspectj.lang.annotation.Around;
//import org.aspectj.lang.annotation.Aspect;
//import org.springframework.stereotype.Component;
//
//import java.util.List;
//
//@Component
//@Aspect
//public class PageAspect {
//    /**
//     * 环绕通知,作用在对象上
//     * @param args
//     * @return
//     * @throws Throwable
//     */
//    @Around("execution(* *..*Service.*Pager(..))")
//    public Object invoke(ProceedingJoinPoint args) throws Throwable{
////        获取目标方法传递过来的参数集合
//        Object[] params=args.getArgs();
////        如果说，传过来的参数有pageBean，那么就进行分页
//        PageBean<U> pageBean=null;
//        for (Object param:params){
//            if(param instanceof  PageBean){
//                pageBean=(PageBean<U>) param;
//                break;
//            }
//        }
//
//        if (pageBean!=null &&pageBean.isPagination()){
//            PageHelper.startPage(pageBean.getPage(),pageBean.getRows());
//        }
//        /* List<Book> books = bookMapper.queryByNamePager(book);*/
//        Object proceed = args.proceed(params);
//        if (pageBean!=null &&pageBean.isPagination()){
//            PageInfo pageInfo = new PageInfo((List) proceed);
//            pageBean.setTotal(pageInfo.getTotal()+"");
//
//        }
//        return proceed;
//    }
//}
//
