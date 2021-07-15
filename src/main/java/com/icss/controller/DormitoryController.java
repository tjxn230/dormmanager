package com.icss.controller;


import com.icss.domain.*;
import com.icss.domain.Class;
import com.icss.service.DormitoryService;
import org.apache.commons.lang.text.StrBuilder;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;

/**
 * 用户控制器类
 */
@Controller
public class DormitoryController {
	// 依赖注入
	@Autowired
	private  DormitoryService dormitoryService;

	/**
	 * 分页查询
	 * pageIndex 当前页码
	 * pageSize  显示条数
	 */
	@RequestMapping(value = "/findDormitory")
	public String findDormitory(String a_name,Integer s_dormitoryid,String d_dormbuilding,
								Integer pageIndex, Integer pageSize, Model model) {

	  PageInfo<Dormitory> di = dormitoryService.findPageInfo(a_name,s_dormitoryid,
			  d_dormbuilding,pageIndex,pageSize);
	  model.addAttribute("di",di);
		return "dormitory_list";
	}


	//    excel
	@RequestMapping(value = "/newexportdormlist")
	public void exportlist(HttpServletRequest request, HttpServletResponse response)throws IOException {
		List<Dormitory> dormitoryList = dormitoryService.getAll();
		//创建一个工作表
		HSSFWorkbook workbook = new HSSFWorkbook();
		//创建标签页
		HSSFSheet sheet = workbook.createSheet("宿舍信息");
		//创建标题行
		HSSFRow row = sheet.createRow(0);
		//在标题行中设置单元格
		row.createCell(0).setCellValue("编号");
		row.createCell(1).setCellValue("宿舍号");
		row.createCell(2).setCellValue("宿舍楼");
		row.createCell(3).setCellValue("床位总数");
		row.createCell(4).setCellValue("可用床位");
		row.createCell(5).setCellValue("宿舍管理员");
		//生成数据行，遍历集合，每一个对象对应一行
		for(int i =0 ;i < dormitoryList.size() ;i++){
			//有多少个用户就得生成几行
			row = sheet.createRow(sheet.getLastRowNum() +1);
			//行创建了添加数据
			row.createCell(0).setCellValue(dormitoryList.get(i).getD_id());
			row.createCell(1).setCellValue(dormitoryList.get(i).getS_dormitoryid());
			row.createCell(2).setCellValue(dormitoryList.get(i).getD_dormbuilding());
			row.createCell(3).setCellValue(dormitoryList.get(i).getD_bedtotal());
			row.createCell(4).setCellValue(dormitoryList.get(i).getD_bed());
			row.createCell(5).setCellValue(dormitoryList.get(i).getA_name());
		}
		//数据生成完毕，下面就要下载了，
		//通过response响应到客户端
		String filename = "宿舍信息.xls";
		//获取内容类型
		String mimeType = request.getServletContext().getMimeType(filename);
		response.setContentType(mimeType);
		//处理文件名
//        filename = filename.substring(0,filename.lastIndexOf("."));//分区数据
		//如果文件名有中文，处理中文的乱码问题
		filename = URLEncoder.encode( filename, "UTF-8");
		response.setHeader("content-disposition", "attachment;filename="+filename);
		//写数据
		workbook.write(response.getOutputStream());
	}
	/**
	 * 导出Excel
	 */
	@RequestMapping(value = "/exportdormitorylist", method = RequestMethod.POST)
	@ResponseBody
	public List<Dormitory> exportDormitory(){
		List<Dormitory> dormitoryList = dormitoryService.getAll();
		return dormitoryList;
	}

	/**
	 * 添加宿舍信息
	 */
	@RequestMapping(value = "/addDormitory" ,method = RequestMethod.POST)
	@ResponseBody
	public String addDormitory( @RequestBody Dormitory dormitory) {
		int d = dormitoryService.addDormitory(dormitory);
		return "dormitory_list";
	}

	/**
	 * 删除宿舍信息
	 */
	@RequestMapping( "/deleteDormitory")
	@ResponseBody
	public String deleteDormitory(Integer d_id) {
		int d = dormitoryService.deleteDormitory(d_id);
		return "dormitory_list";
	}

	/**
	 * 修改学生信息
	 */
	@RequestMapping( "/updateDormitory")
	public String updateDormitory( Dormitory dormitory) {
		int d = dormitoryService.updateDormitory(dormitory);
		return "redirect:/findDormitory";
	}


	@RequestMapping( "/findDormitoryById")
	public String findDormitoryById(Integer d_id,HttpSession session) {

		Dormitory d= dormitoryService.findDormitoryById(d_id);
		session.setAttribute("d",d);
		return "dormitory_edit";
	}

	/**
	 * 宿舍人员信息查询
	 */
	@RequestMapping(value = "/findDormitoryStudent")
	public String findDormitoryStudent(Dormitory dormitory,Model model) {
		List<Dormitory> d = dormitoryService.findDormitoryStudent(dormitory);
		model.addAttribute("ds",d);
		return "dormitory_Studentlist";
	}

//根据宿舍房间号和宿舍楼查询宿舍信息
	@RequestMapping("/selectdorminfo")
	public String findstudentcardbysid(Integer id,String building, Model model) {
		DormitoryInfo dormitoryInfo = dormitoryService.selectdormitory(id,building);
		int grade =dormitoryInfo.getD_grade();
		if (grade<=4){
			model.addAttribute("msg","寝室卫生环境极其恶劣，请立即打扫卫生，该寝室将被通报批评");
		}if(grade >4 && grade <= 6){
			model.addAttribute("msg","寝室卫生环境恶劣，需要打扫卫生，请注意寝室卫生");
		}if(grade > 6 && grade <= 8 ){
			model.addAttribute("msg","寝室卫生良好，继续保持");
		}if(grade >8 ){
			model.addAttribute("msg","寝室卫生优秀，该宿舍将被通报表扬");
		}
		model.addAttribute("dormitoryInfo", dormitoryInfo);
		return "dorminfo";
	}

//	根据ajax查询有空床的宿舍号
	@RequestMapping("selectdormid")
	@ResponseBody
	public List selectdormid(){
		List<Integer> integers = dormitoryService.selectsparedormid();
		return integers;
}

	//ajax根据有空床的宿舍号查询宿舍楼信息
	@RequestMapping("selectbydormid")
	@ResponseBody
	public GridBean selectbydormid(Integer s_dormitoryid){
		List<DormitoryInfo>  dormitoryInfos=dormitoryService.selectbydormid(s_dormitoryid);
		return new GridBean(0,"宿舍楼信息",dormitoryInfos.size(),dormitoryInfos);
	}

//	把选好的宿舍存入数据库
	@RequestMapping("/exchangedorm")
	public String exchangedorm(Integer s_studentid,Integer s_dormitoryid,String d_dormbuilding, Model model) {
//		首先判断该学生有无宿舍，分2个分支,其中一个分支在判断有宿舍的是不是要换宿
		String flag = dormitoryService.havaornot(s_studentid);
		if(flag.equals("0")){
			System.out.println("该学生无宿舍");
			int nums = dormitoryService.huandorm(s_studentid,s_dormitoryid,d_dormbuilding);
			int nums1 =dormitoryService.huandormds(s_studentid,s_dormitoryid);
			int nums2 =dormitoryService.decreasebed(s_dormitoryid);
			model.addAttribute("msg","学生已成功选择宿舍，请找宿舍管理员签字并入住宿舍，请重新登录学生端来查看信息");
		}else{
			Integer integer = dormitoryService.beforedorm(s_studentid);
			if(integer != s_dormitoryid) {
				System.out.println("该学生有宿舍");
				int num = dormitoryService.increasebed(integer);
				int num1 = dormitoryService.huandorm(s_studentid, s_dormitoryid, d_dormbuilding);
				int num2 = dormitoryService.huandormds(s_studentid, s_dormitoryid);
				int num3 = dormitoryService.decreasebed(s_dormitoryid);
				model.addAttribute("msg", "学生申请换宿成功，请找宿舍管理员签字并入住宿舍，请重新登录学生端来查看信息");
				}
			}
	return "info2";
}
}
