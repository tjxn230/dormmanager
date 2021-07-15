package com.icss.controller;

/**
 * @author Logan
 * @date 2021/7/4 - 6:53
 */

import com.icss.domain.DormClean;
import com.icss.domain.Dormitory;
import com.icss.domain.PageInfo;
import com.icss.service.DormCleanService;
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
 * @description: 宿舍卫生控制器
 **/

@Controller
public class DormCleanController {

    //依赖注入
    @Autowired
    private DormCleanService dormCleanService;

    /**
     * 分页查询
     * pageIndex 当前页码
     * pageSize  显示条数
     */
    @RequestMapping(value = "/findDormClean")
    public String findDormClean(Integer d_id,String d_dormbuilding,
                                Integer pageIndex, Integer pageSize, Model model) {

        PageInfo<DormClean> di = dormCleanService.findPageInfo(d_id,d_dormbuilding,
                pageIndex,pageSize);
        model.addAttribute("di",di);
        return "dormclean_list";
    }



    //    excel
    @RequestMapping(value = "/newexportdormcleanlist")
    public void exportlist(HttpServletRequest request, HttpServletResponse response)throws IOException {
        List<DormClean> dormCleanList = dormCleanService.getAll();
        //创建一个工作表
        HSSFWorkbook workbook = new HSSFWorkbook();
        //创建标签页
        HSSFSheet sheet = workbook.createSheet("宿舍卫生信息");
        //创建标题行
        HSSFRow row = sheet.createRow(0);
        //在标题行中设置单元格
        row.createCell(0).setCellValue("编号");
        row.createCell(1).setCellValue("宿舍号");
        row.createCell(2).setCellValue("宿舍楼");
        row.createCell(3).setCellValue("宿舍卫生");
        row.createCell(4).setCellValue("创建日期");
        row.createCell(5).setCellValue("更新日期");
        //生成数据行，遍历集合，每一个对象对应一行
        for(int i =0 ;i < dormCleanList.size() ;i++){
            //有多少个用户就得生成几行
            row = sheet.createRow(sheet.getLastRowNum() +1);
            //行创建了添加数据dormCleanList
            row.createCell(0).setCellValue(dormCleanList.get(i).getG_id());
            row.createCell(1).setCellValue(dormCleanList.get(i).getD_id());
            row.createCell(2).setCellValue(dormCleanList.get(i).getD_dormbuilding());
            row.createCell(3).setCellValue(dormCleanList.get(i).getD_grade());
            row.createCell(4).setCellValue(dormCleanList.get(i).getCreate_time());
            row.createCell(5).setCellValue(dormCleanList.get(i).getUpdate_time());
        }
        //数据生成完毕，下面就要下载了，
        //通过response响应到客户端
        String filename = "宿舍卫生信息.xls";
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
    @RequestMapping(value = "/exportdormcleanlist", method = RequestMethod.POST)
    @ResponseBody
    public List<DormClean> exportDormclean(){
        List<DormClean> dormclean = dormCleanService.getAll();
        return dormclean;
    }

    /**
     * 添加宿舍卫生信息
     */
    @RequestMapping(value = "/addDormClean" ,method = RequestMethod.POST)
    @ResponseBody
    public String addDormClean( @RequestBody DormClean dormclean) {
        int d = dormCleanService.addDormClean(dormclean);
        return "dormclean_list";
    }

    /**
     * 删除宿舍卫生信息
     */
    @RequestMapping( "/deleteDormClean")
    @ResponseBody
    public String deleteDormClean(Integer g_id) {
        int d = dormCleanService.deleteDormClean(g_id);
        return "dormclean_list";
    }

    /**
     * 修改宿舍卫生信息
     */
    @RequestMapping( "/updateDormClean")
    public String updateDormClean( DormClean dormclean) {
        int d = dormCleanService.updateDormClean(dormclean);
        return "redirect:/findDormClean";
    }


    @RequestMapping( "/findDormCleanById")
    public String findDormCleanById(Integer g_id, HttpSession session) {

        DormClean d= dormCleanService.findDormCleanById(g_id);
        session.setAttribute("d",d);
        return "dormclean_edit";
    }


}

