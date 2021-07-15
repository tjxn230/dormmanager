package com.icss.controller;

/**
 * @author Logan
 * @date 2021/7/3 - 22:20
 */

import com.icss.domain.Class;

import com.icss.domain.PageInfo;
import com.icss.domain.Visitor;
import com.icss.service.ClassService;
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
import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;

/**
 * 用户控制器类
 */
@Controller
public class ClassController {
    // 依赖注入
    @Autowired
    private ClassService classService;

    /**
     * 分页查询
     * pageIndex 当前页码
     * pageSize  显示条数
     */
    @RequestMapping(value = "/findClass")
    public String findClass(Integer c_classid, String c_classname, String c_counsellor,
                            Integer pageIndex, Integer pageSize, Model model) {

        PageInfo<Class> ci = classService.findPageInfo(c_classname,c_counsellor,
                c_classid,pageIndex,pageSize);
        model.addAttribute("ci",ci);
        model.addAttribute("c_classid",c_classid);
        return "class_list";
    }

    //    excel
    @RequestMapping(value = "/newexportclasslist")
    public void exportlist(HttpServletRequest request, HttpServletResponse response)throws IOException {
        List<Class> classList = classService.getAll();
        //创建一个工作表
        HSSFWorkbook workbook = new HSSFWorkbook();
        //创建标签页
        HSSFSheet sheet = workbook.createSheet("班级信息");
        //创建标题行
        HSSFRow row = sheet.createRow(0);
        //在标题行中设置单元格
        row.createCell(0).setCellValue("编号");
        row.createCell(1).setCellValue("班级名");
        row.createCell(2).setCellValue("班级名字");
        row.createCell(3).setCellValue("班级辅导员");
        //生成数据行，遍历集合，每一个对象对应一行
        for(int i =0 ;i < classList.size() ;i++){
            //有多少个用户就得生成几行
            row = sheet.createRow(sheet.getLastRowNum() +1);
            //行创建了添加数据
            row.createCell(0).setCellValue(classList.get(i).getC_id());
            row.createCell(1).setCellValue(classList.get(i).getC_classid());
            row.createCell(2).setCellValue(classList.get(i).getC_classname());
            row.createCell(3).setCellValue(classList.get(i).getC_counsellor());
        }
        //数据生成完毕，下面就要下载了，
        //通过response响应到客户端
        String filename = "班级信息.xls";
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
    @RequestMapping(value = "/exportclasslist", method = RequestMethod.POST)
    @ResponseBody
    public List<Class> exportClass(){
        List<Class> classList = classService.getAll();
        return classList;
    }

    /**
     * 删除学生信息
     */
    @RequestMapping( "/deleteClass")
    @ResponseBody
    public String deleteClass(Integer c_id) {
        int c = classService.deleteClass(c_id);
        return "class_list";
    }

    /**
     * 添加班级信息
     */
    @RequestMapping(value = "/addClass" ,method = RequestMethod.POST)
    @ResponseBody
    public String addClass( @RequestBody Class uclass) {
        int c = classService.addClass(uclass);
        return "class_list";
    }

    @RequestMapping( "/findClassById")
    public String findClassById(Integer c_id,Model model) {
        Class c= classService.findClassById(c_id);
        model.addAttribute("c",c);
        return "class_edit";
    }

    /**
     * 修改班级信息
     */
    @RequestMapping(value = "/updateClass" ,method = RequestMethod.POST)

    public String updateClass( Class uclass) {
        int c = classService.updateClass(uclass);
        return "redirect:/findClass";
    }

    /**
     * 班级人员信息查询
     */
    @RequestMapping(value = "/findClassStudent")
    public String findClassStudent(String c_classname,Integer c_classid, Model model) {
        List<Class> c = classService.findClassStudent(c_classname, c_classid);
        model.addAttribute("cs",c);
        return "class_Studentlist";
    }

    //采用Ajax来提交表单，并返回JSON数据
//	@RequestMapping(value = "/findClassStudentlist",method = RequestMethod.POST)
//	@ResponseBody
//	public List<Class> findClassStudentlist(@RequestBody Class uclass){
//		List<Class> c = classService.findClassStudent(uclass);
//		return c;
//	}
}
