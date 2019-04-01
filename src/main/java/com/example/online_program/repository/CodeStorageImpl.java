package com.example.online_program.repository;

import com.example.online_program.entity.CodeInfo;
import com.example.online_program.impl.CodeStorage;
import com.example.online_program.utils.MybatisUtils;
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
                session.insert("saveCode",codeInfo);
                session.commit();
                MybatisUtils.closeSqlSession(session);
                return true;
            }
        }
        return false;
    }
}
