package com.icss.dao;

import com.icss.domain.Student;
import com.icss.domain.StudentAccount;
import com.icss.domain.StudentCard;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 管理员DAO层接口
 */
public interface StudentDao {
    /**
     * 进行分页查询
     */

    //获取总条数
    public Integer totalCount(@Param("s_name") String s_name, @Param("s_studentid")Integer s_studentid,
                              @Param("s_classid")Integer s_classid, @Param("s_classname")String s_classname);
    //获取用户列表
    public List<Student> getStudentList(@Param("s_name") String s_name, @Param("s_studentid")Integer s_studentid, @Param("s_classid")Integer s_classid,
                                        @Param("s_classname")String s_classname, @Param("currentPage")Integer currentPage, @Param("pageSize")Integer pageSize);

    public int deleteStudent(Integer s_id);   //删除学生信息
    public int addStudent(Student student);   //添加学生信息
    public int updateStudent(Student student); //修改学生信息
    public Student findStudentById(Integer s_id);
    public List<Student> getAll();
//   查询学生卡账户
    public List<StudentAccount> findStudentAccount();

//    学生端登录
    public StudentAccount studentlogin(String phone,String password);
//    学生卡查询
    public StudentCard findstudentcardbysid(Integer s_studentid);
//    查询欠款
    public StudentAccount selectdebtbyid(Integer s_studentid);
//    修改存款
    public int updateaccount(String s_phone,Double s_account,String s_payway);
    //    修改存款
    public int paymoney(Integer s_studentid,Double s_account,Double s_debt);
//    增加学生账户记录
    public int addAccount(StudentAccount studentAccount);

//  修改密码
    public int updatepassword(Integer s_studentid,String s_password);

    //  修改欠款
    public int updatedebt(Integer s_studentid,Double s_debt);
}

