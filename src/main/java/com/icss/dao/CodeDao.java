package com.icss.dao;

import com.icss.domain.Code;

public interface CodeDao {
    public int insertCode(Code code);
    public Code findcode(String phone);
}
