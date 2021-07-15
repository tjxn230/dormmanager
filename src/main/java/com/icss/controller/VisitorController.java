package com.icss.controller;

import com.icss.domain.PageInfo;
import com.icss.domain.Visitor;
import com.icss.service.VisitorService;
import com.icss.util.sendcode;
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
 * @program: dormitorySystem
 * @description: 访客管理
 **/
@Controller
public class VisitorController {
    //依赖注入
    @Autowired
    private VisitorService visitorService;

    /**
     * 分页查询
     * pageIndex 当前页码
     * pageSize  显示条数
     */
    @RequestMapping(value = "/findVisitor")
    public String findVisitor(String v_name, Integer v_phone , Integer pageIndex,
                              Integer pageSize, Model model) {

        PageInfo<Visitor> pi = visitorService.findPageInfo(v_name,v_phone,
                pageIndex,pageSize);
        model.addAttribute("pi",pi);
        model.addAttribute("v_name",v_name);
        return "visitor_list";
    }

//    excel
    @RequestMapping(value = "/newexportvisitorlist")
    public void exportllist(HttpServletRequest request, HttpServletResponse response)throws IOException {
        List<Visitor> visitorList = visitorService.getAll();
        //创建一个工作表
        HSSFWorkbook workbook = new HSSFWorkbook();
        //创建标签页
        HSSFSheet sheet = workbook.createSheet("访客信息");
        //创建标题行
        HSSFRow row = sheet.createRow(0);
        //在标题行中设置单元格
        row.createCell(0).setCellValue("编号");
        row.createCell(1).setCellValue("访客名");
        row.createCell(2).setCellValue("访客手机号");
        row.createCell(3).setCellValue("访问宿舍号");
        row.createCell(4).setCellValue("访问宿舍楼号");
        row.createCell(5).setCellValue("访问时间");
        //生成数据行，遍历集合，每一个对象对应一行
        for(int i =0 ;i < visitorList.size() ;i++){
            //有多少个用户就得生成几行
            row = sheet.createRow(sheet.getLastRowNum() +1);
            //行创建了添加数据
            row.createCell(0).setCellValue(visitorList.get(i).getV_id());
            row.createCell(1).setCellValue(visitorList.get(i).getV_name());
            row.createCell(2).setCellValue(visitorList.get(i).getV_phone());
            row.createCell(3).setCellValue(visitorList.get(i).getV_dormitoryid());
            row.createCell(4).setCellValue(visitorList.get(i).getV_dormbuilding());
            row.createCell(5).setCellValue(visitorList.get(i).getCreate_time());
        }
        //数据生成完毕，下面就要下载了，
        //通过response响应到客户端
        String filename = "访客信息.xls";
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
    @RequestMapping(value = "/exportvisitorlist", method = RequestMethod.POST)
    @ResponseBody
    public List<Visitor> exportVisitor(){
        List<Visitor> visitorList = visitorService.getAll();
        return visitorList;
    }

    /**
     * 添加学生信息
     */

    @RequestMapping(value = "/addVisitor" ,method = RequestMethod.POST)
    @ResponseBody
    public String addStudent(@RequestBody Visitor visitor) {
        int v = visitorService.addVisitor(visitor);
        return "visitor_list";
    }
}

