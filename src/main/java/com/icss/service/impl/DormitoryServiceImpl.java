package com.icss.service.impl;

/**
 * @author Logan
 * @date 2021/7/3 - 22:33
 */

import com.icss.dao.DormitoryDao;
import com.icss.domain.Dormitory;
import com.icss.domain.DormitoryInfo;
import com.icss.domain.PageInfo;
import com.icss.service.DormitoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 用户Service接口实现类
 */
@Service("dormitoryService")
@Transactional
public class DormitoryServiceImpl implements DormitoryService {
    // classDao
    @Autowired
    private DormitoryDao dormitoryDao;


    //分页查询
    @Override
    public PageInfo<Dormitory> findPageInfo(String a_name, Integer s_dormitoryid, String d_dormbuilding, Integer pageIndex, Integer pageSize) {
        PageInfo<Dormitory> pi = new PageInfo<Dormitory>();
        pi.setPageIndex(pageIndex);
        pi.setPageSize(pageSize);
        //获取总条数
        Integer totalCount = dormitoryDao.totalCount(a_name,s_dormitoryid,d_dormbuilding);
        if (totalCount>0){
            pi.setTotalCount(totalCount);
            //每一页显示宿舍信息数
            //currentPage = (pageIndex-1)*pageSize  当前页码数减1*最大条数=开始行数
            List<Dormitory> dormitoryList =	dormitoryDao.getDormitoryList(a_name,s_dormitoryid,d_dormbuilding,
                    (pi.getPageIndex()-1)*pi.getPageSize(),pi.getPageSize());
            pi.setList(dormitoryList);
        }
        return pi;
    }

    @Override
    public List<Dormitory> getAll(){
        List<Dormitory> dormitoryList = dormitoryDao.getAll();
        return dormitoryList;
    }

    //添加宿舍信息
    @Override
    public int addDormitory(Dormitory dormitory) {
        return dormitoryDao.addDormitory(dormitory);
    }

    //通过id删除宿舍信息
    @Override
    public int deleteDormitory(Integer d_id) {
        return dormitoryDao.deleteDormitory(d_id);
    }

    //修改宿舍信息
    @Override
    public int updateDormitory(Dormitory dormitory) {
        return dormitoryDao.updateDormitory(dormitory);
    }

    @Override
    public Dormitory findDormitoryById (Integer d_id){
        Dormitory d = dormitoryDao.findDormitoryById(d_id);
        return  d;
    }
    //查询宿舍人员信息
    @Override
    public List<Dormitory> findDormitoryStudent(Dormitory dormitory) {
        List<Dormitory> d = dormitoryDao.findDormitoryStudent(dormitory);
        return d;
    }
//根据宿舍号宿舍楼查宿舍信息
    @Override
    public DormitoryInfo selectdormitory(Integer id, String building) {
        DormitoryInfo dormitoryInfo = dormitoryDao.selectdormitory(id, building);
        return dormitoryInfo;
    }
    //	查询有空床宿舍号
    @Override
    public List<Integer> selectsparedormid() {
        List<Integer> integers = dormitoryDao.selectsparedormid();
        return integers;
    }
    //ajax根据有空床的宿舍号查询宿舍楼信息
    @Override
    public List<DormitoryInfo> selectbydormid(Integer s_dormitoryid) {
        List<DormitoryInfo> list = dormitoryDao.selectbydormid(s_dormitoryid);
        return list;
    }
    //	查询学生是否有宿舍
    @Override
    public String havaornot(Integer s_studentid) {
        String s1 = dormitoryDao.havaornot(s_studentid);
        return s1;
    }
    // 学生申请换宿，更改宿舍表d_student
    @Override
    public int huandorm(Integer s_studentid, Integer s_dormitoryid, String s_dormbuilding) {
        int nums = dormitoryDao.huandorm(s_studentid, s_dormitoryid, s_dormbuilding);
        return nums;
    }

    //	     学生申请换宿，更改宿舍表d_stgrade
    @Override
    public int huandormds(Integer s_studentid, Integer s_dormitoryid) {
        int nums = dormitoryDao.huandormds(s_studentid, s_dormitoryid);
        return nums;
    }

    //	学生之前没有宿舍,床位-1,dormitoryinfo表
    @Override
    public int decreasebed(Integer s_dormitoryid) {
        int nums = dormitoryDao.decreasebed(s_dormitoryid);
        return nums;
    }

    //	学生之前有宿舍，先退宿，床位+1，更改dormitoryinfo ,dormitoryinfo表
    @Override
    public int increasebed(Integer s_dormitoryid) {
        int nums = dormitoryDao.increasebed(s_dormitoryid);
        return nums;
    }
    //	查询学生之前的宿舍

    @Override
    public Integer beforedorm(Integer beforestudentid) {
        Integer integer = dormitoryDao.beforedorm(beforestudentid);
        return integer;
    }
}
