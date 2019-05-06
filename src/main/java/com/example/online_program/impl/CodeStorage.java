package com.example.online_program.impl;

import com.example.online_program.entity.CodeInfo;

/**
 * @Author: wtt
 * @Date: 19-4-1
 * @Description:
 */
public interface CodeStorage {
    /**
     *
     * @param codeInfo
     */
    public boolean save(CodeInfo codeInfo);

    /**
     * @param codeId
     * @return
     */
    public String show(String codeId);
}
