package com.icss.service.impl;

import com.icss.dao.CodeDao;
import com.icss.domain.Code;
import com.icss.service.CodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("codeService")
@Transactional
public class CodeServiceImpl implements CodeService {
    @Autowired
    private CodeDao codeDao;

    @Override
    public int insertCode(Code code) {
        int nums = codeDao.insertCode(code);
        return nums;
    }

    @Override
    public Code findCode(String phone) {
        Code code = codeDao.findcode(phone);
        return code;
    }
}
