package com.icss.service.impl;

import com.icss.dao.StudentDao;
import com.icss.domain.PageInfo;
import com.icss.domain.Student;
import com.icss.domain.StudentAccount;
import com.icss.domain.StudentCard;
import com.icss.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 用户Service接口实现类
 */
@Service("studentService")
@Transactional
public class StudentServiceImpl implements StudentService {
    // 注入studentDao
    @Autowired
    private StudentDao studentDao;


    //分页查询
    @Override
    public PageInfo<Student> findPageInfo(String s_name, Integer s_studentid, Integer s_classid,
                                          String s_classname, Integer pageIndex, Integer pageSize) {
        PageInfo<Student> pi = new PageInfo<Student>();
        pi.setPageIndex(pageIndex);
        pi.setPageSize(pageSize);
        //获取总条数
        Integer totalCount = studentDao.totalCount(s_name,s_studentid,s_classid,s_classname);
        if (totalCount>0){
            pi.setTotalCount(totalCount);
            //每一页显示学生信息数
            //currentPage = (pageIndex-1)*pageSize  当前页码数减1*最大条数=开始行数
            List<Student> studentList =	studentDao.getStudentList(s_name,s_studentid,s_classid,s_classname,
                    (pi.getPageIndex()-1)*pi.getPageSize(),pi.getPageSize());
            pi.setList(studentList);
        }
        return pi;
    }


    @Override
    public List<Student> getAll(){
        List<Student> studentList = studentDao.getAll();
        return studentList;
    }

    //通过id删除学生信息
    @Override
    public int deleteStudent(Integer s_id) {
        return studentDao.deleteStudent(s_id);
    }
    //添加学生信息
    @Override
    public  int addStudent(Student student) {
        return studentDao.addStudent(student);
    }
    //修改学生信息
    @Override
    public int updateStudent(Student student) { return studentDao.updateStudent(student); }

    @Override
    public Student findStudentById (Integer s_id){
        Student s = studentDao.findStudentById(s_id);
        return  s;
    }

    //    查询学生卡
    @Override
    public List<StudentAccount> findStudentAccount() {
        List<StudentAccount> studentAccounts= studentDao.findStudentAccount();
        return studentAccounts;
    }

    //学生端登录
    @Override
    public StudentAccount studentlogin(String phone, String password) {
        StudentAccount studentAccount = studentDao.studentlogin(phone,password);
        return studentAccount;
    }
//    学生卡查询

    @Override
    public StudentCard findstudentcardbysid(Integer id) {
        StudentCard studentCard =studentDao.findstudentcardbysid(id);
        return studentCard;
    }
//    通过id查学生卡

    @Override
    public StudentAccount selectdebtbyid(Integer id) {
        StudentAccount studentAccount =studentDao.selectdebtbyid(id);
        return studentAccount;
    }
// 充值金额
    @Override
    public int updateaccount(String s_phone, Double s_account, String s_payway) {
        int nums = studentDao.updateaccount(s_phone,s_account,s_payway);
        return nums;
    }
//缴费
    @Override
    public int paymoney(Integer stuid, Double account, Double debt) {
        int nums = studentDao.paymoney(stuid, account, debt);
        return nums;
    }
//   增加一个学生账户

    @Override
    public int addAccount(StudentAccount studentAccount) {
        int nums = studentDao.addAccount(studentAccount);
        return nums;
    }
//修改学生账户密码
    @Override
    public int updatepassword(Integer s_studentid, String s_password) {
        int nums = studentDao.updatepassword(s_studentid, s_password);
        return nums;
    }
//修改学生账户欠款
    @Override
    public int updatedebt(Integer s_studentid, Double s_debt) {
        int nums = studentDao.updatedebt(s_studentid, s_debt);
        return nums;
    }
}

