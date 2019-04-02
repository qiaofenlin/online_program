package com.example.online_program.repository;

import com.example.online_program.entity.CodeInfo;
import com.example.online_program.impl.CodeStorage;
import com.example.online_program.utils.MybatisUtils;
import com.example.online_program.utils.Utils;
import org.apache.ibatis.session.SqlSession;

/**
 * @Author: wtt
 * @Date: 19-4-1
 * @Description:
 */
public class CodeStorageImpl implements CodeStorage {
    @Override
    public String show(String codeId) {
        String result = null;
        if (codeId!=null&&!codeId.trim().equals("")){
            SqlSession session = MybatisUtils.getSqlSession();
            if (session!=null){
                result = session.selectOne("showCode",codeId);
            }
            MybatisUtils.closeSqlSession(session);
        }
        return result;
    }

    @Override
    public boolean save(CodeInfo codeInfo) {
        if (codeInfo!=null){
            SqlSession session = MybatisUtils.getSqlSession();
            if (session!=null){
                if (this.isExitsCode(codeInfo.getCodeId())){
                    codeInfo.setUpdateTime(Utils.getTimeStamp());
                    session.update("updateCode",codeInfo);
                    System.out.println("update codeId : "+codeInfo.getCodeId());
                    System.out.println("update time : "+codeInfo.getUpdateTime());
                }else {
                    session.insert("saveCode",codeInfo);
                }
                session.commit();
                MybatisUtils.closeSqlSession(session);
                return true;
            }
        }
        return false;
    }

    public boolean isExitsCode(String codeId){
        SqlSession session = MybatisUtils.getSqlSession();
        if (session!=null&&codeId!=null&&!codeId.trim().equals("")){
            int i = session.selectOne("isExistsCode",codeId);
            System.out.println("isExistsCode -- i : "+i);
            if (i>0) {
                if (i>1){
                    System.out.println("[ DB data is exeception !!]");
                }
                return true;
            }
            MybatisUtils.closeSqlSession(session);
        }
        return false;
    }

    public static void main(String[] args) {
        CodeStorageImpl codeStorage = new CodeStorageImpl();
//        codeStorage.isExitsCode("n2e33a9a5f6f4dc79d0edb181b001331");
        CodeInfo codeInfo = new CodeInfo();
        codeInfo.setCodeId("n2e33a9a5f6f4dc79d0edb181b001331");
        codeInfo.setCodeText("xxx");
        codeInfo.setUserId("1111");
        codeInfo.setUpdateTime(Utils.getTimeStamp());
        codeStorage.save(codeInfo);
    }
}
