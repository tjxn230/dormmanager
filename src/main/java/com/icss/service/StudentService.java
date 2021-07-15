package com.icss.service;

import com.icss.domain.PageInfo;
import com.icss.domain.Student;
import com.icss.domain.StudentAccount;
import com.icss.domain.StudentCard;

import java.util.List;

/**
 * 用户Service层接口
 */
public interface StudentService {

    //分页查询
    public PageInfo<Student> findPageInfo(String s_name, Integer s_studentid, Integer s_classid,
                                          String s_classname, Integer pageIndex, Integer pageSize);

    public int deleteStudent(Integer s_id);   //通过id删除学生信息
    public int addStudent(Student student);   //添加学生信息
    public int updateStudent(Student student); //修改学生信息
    public Student findStudentById(Integer s_id);
    public List<Student> getAll();
//  学生端登录
    public StudentAccount studentlogin(String phone, String password);

    //    查询学生卡账户
    public List<StudentAccount> findStudentAccount();
//    学生卡查询
    public StudentCard findstudentcardbysid(Integer id);
//  通过id查学生账户
    public StudentAccount selectdebtbyid(Integer id);
//   修改存款
    public int updateaccount(String s_phone,Double s_account,String s_payway);
//    缴费
    public int paymoney(Integer stuid,Double account,Double debt);
//    增加一个学生账户
    public int addAccount(StudentAccount studentAccount);

    //  修改密码
    public int updatepassword(Integer s_studentid,String s_password);

    //  修改欠款
    public int updatedebt(Integer s_studentid,Double s_debt);
}

