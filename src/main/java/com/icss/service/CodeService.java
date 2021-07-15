package com.icss.service;

import com.icss.domain.Code;

public interface CodeService {
    public int insertCode(Code code);
    public Code findCode(String phone);
}
