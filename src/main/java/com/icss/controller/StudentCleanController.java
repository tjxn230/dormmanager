package com.icss.controller;

import com.icss.domain.Dormitory;
import com.icss.domain.PageInfo;
import com.icss.domain.StudentClean;
import com.icss.service.StudentCleanService;
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
 * @program: dormitorySystem
 * @description: 学生卫生控制器
 **/
@Controller
public class StudentCleanController {
    //依赖注入
    @Autowired
    private StudentCleanService studentCleanService;

    /**
     * 分页查询
     * pageIndex 当前页码
     * pageSize  显示条数
     */
    @RequestMapping(value = "/findStudentClean")
    public String findDormClean(Integer s_studentid, String s_name, Integer s_dormitoryid, Integer pageIndex, Integer pageSize, Model model) {

        PageInfo<StudentClean> di = studentCleanService.findPageInfo(s_studentid,s_name,s_dormitoryid,pageIndex,pageSize);
        model.addAttribute("di",di);
        return "studentclean_list";
    }



    //    excel
    @RequestMapping(value = "/newexportstuclenlist")
    public void exportlist(HttpServletRequest request, HttpServletResponse response)throws IOException {
        List<StudentClean> studentCleanLists = studentCleanService.getAll();
        //创建一个工作表
        HSSFWorkbook workbook = new HSSFWorkbook();
        //创建标签页
        HSSFSheet sheet = workbook.createSheet("学生卫生信息");
        //创建标题行
        HSSFRow row = sheet.createRow(0);
        //在标题行中设置单元格
        row.createCell(0).setCellValue("编号");
        row.createCell(1).setCellValue("学号");
        row.createCell(2).setCellValue("学生姓名");
        row.createCell(3).setCellValue("卫生评分");
        row.createCell(4).setCellValue("班级编号");
        row.createCell(5).setCellValue("宿舍号");
        row.createCell(6).setCellValue("创建时间");
        row.createCell(7).setCellValue("更新时间");
        //生成数据行，遍历集合，每一个对象对应一行
        for(int i =0 ;i < studentCleanLists.size() ;i++){
            //有多少个用户就得生成几行
            row = sheet.createRow(sheet.getLastRowNum() +1);
            //行创建了添加数据
            row.createCell(0).setCellValue(studentCleanLists.get(i).getG_id());
            row.createCell(1).setCellValue(studentCleanLists.get(i).getS_studentid());
            row.createCell(2).setCellValue(studentCleanLists.get(i).getS_name());
            row.createCell(3).setCellValue(studentCleanLists.get(i).getS_grade());
            row.createCell(4).setCellValue(studentCleanLists.get(i).getS_classid());
            row.createCell(5).setCellValue(studentCleanLists.get(i).getS_dormitoryid());
            row.createCell(6).setCellValue(studentCleanLists.get(i).getCreate_time());
            row.createCell(7).setCellValue(studentCleanLists.get(i).getUpdate_time());
        }
        //数据生成完毕，下面就要下载了，
        //通过response响应到客户端
        String filename = "学生卫生信息.xls";
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
    @RequestMapping(value = "/exportstudentcleanlist", method = RequestMethod.POST)
    @ResponseBody
    public List<StudentClean> exportStudentclean(){
        List<StudentClean> studentCleanList = studentCleanService.getAll();
        return studentCleanList;
    }

    /**
     * 添加宿舍卫生信息
     */
    @RequestMapping(value = "/addStudentClean" ,method = RequestMethod.POST)
    @ResponseBody
    public String addDormClean( @RequestBody StudentClean studentclean) {
        int d = studentCleanService.addStudentClean(studentclean);
        return "studentclean_list";
    }

    /**
     * 删除宿舍卫生信息
     */
    @RequestMapping( "/deleteStudentClean")
    @ResponseBody
    public String deleteDormClean(Integer g_id) {
        int d = studentCleanService.deleteStudentClean(g_id);
        return "studentclean_list";
    }

    /**
     * 修改宿舍卫生信息
     */
    @RequestMapping( "/updateStudentClean")
    public String updateDormClean( StudentClean studentclean) {
        int d = studentCleanService.updateStudentClean(studentclean);
        return "redirect:/findStudentClean";
    }


    @RequestMapping( "/findStudentCleanById")
    public String findDormCleanById(Integer g_id, HttpSession session) {

        StudentClean d= studentCleanService.findStudentCleanById(g_id);
        session.setAttribute("d",d);
        return "studentclean_edit";
    }

//    根据宿舍房间号查询个人卫生等级
    @RequestMapping( "/stugrade")
    public String stugrade(Integer dormitoryid,Model model) {

        List<StudentClean> studentCleans = studentCleanService.stugrade(dormitoryid);
        model.addAttribute("studentCleans", studentCleans);
        return "studentclean";
}
}