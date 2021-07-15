package com.icss.dao;

import com.icss.domain.Dormitory;
import com.icss.domain.DormitoryInfo;
import org.apache.ibatis.annotations.Param;


import java.util.List;

/**
 * 管理员DAO层接口
 */
public interface DormitoryDao {
	/**
	 * 进行分页查询
	 */

	 //获取总条数
     public Integer totalCount(@Param("a_name") String a_name, @Param("s_dormitoryid") Integer s_dormitoryid, @Param("d_dormbuilding") String d_dormbuilding);
	 //获取用户列表
     public List<Dormitory> getDormitoryList(@Param("a_name") String a_name, @Param("s_dormitoryid") Integer s_dormitoryid, @Param("d_dormbuilding") String d_dormbuilding, @Param("currentPage") Integer currentPage, @Param("pageSize") Integer pageSize);

	public int addDormitory(Dormitory dormitory);    //添加宿舍信息
	public int deleteDormitory(Integer d_id);   //删除宿舍信息
	public int updateDormitory(Dormitory dormitory); //修改宿舍信息
	public Dormitory findDormitoryById(Integer d_id);

	public List<Dormitory> findDormitoryStudent(Dormitory dormitory);//查询宿舍人员信息
	public List<Dormitory> getAll();

//	根据宿舍号宿舍楼查询宿舍信息
	public DormitoryInfo selectdormitory(Integer d_id,String d_dormbuilding);

//	查询有空床宿舍号
	public List<Integer> selectsparedormid();

	//ajax根据有空床的宿舍号查询宿舍楼信息
	public List<DormitoryInfo> selectbydormid(Integer s_dormitoryid);

//	查询学生是否有宿舍
	public String havaornot(Integer s_studentid);

	// 学生申请换宿，更改宿舍表d_student
	public int huandorm(Integer s_studentid,Integer s_dormitoryid,String s_dormbuilding);

//	     学生申请换宿，更改宿舍表d_stgrade
	public int huandormds(Integer s_studentid,Integer s_dormitoryid);
//	学生之前没有宿舍,床位-1,dormitoryinfo表
	public int decreasebed(Integer s_dormitoryid);
//	学生之前有宿舍，先退宿，床位+1，更改dormitoryinfo ,dormitoryinfo表
	public int increasebed(Integer s_dormitoryid);

//	查询学生之前的宿舍
	public Integer beforedorm(Integer s_studentid);
}
