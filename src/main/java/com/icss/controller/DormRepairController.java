package com.icss.controller;

import com.icss.domain.DormRepair;
import com.icss.domain.Dormitory;
import com.icss.domain.PageInfo;
import com.icss.service.DormRepairService;
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
import java.util.Date;
import java.util.List;

/**
 * @program: dormitorySystem
 * @description: 维修登记
 **/
@Controller
public class DormRepairController {
    // 依赖注入
    @Autowired
    private DormRepairService dormRepairService;

    /**
     * 分页查询
     * pageIndex 当前页码
     * pageSize  显示条数
     */
    @RequestMapping(value = "/findDormRepair")
    public String findDormRepair(Integer d_id,String d_dormbuilding,
                                 Integer pageIndex, Integer pageSize, Model model) {

        PageInfo<DormRepair> di = dormRepairService.findPageInfo(d_id,d_dormbuilding,
                pageIndex,pageSize);
        model.addAttribute("di",di);
        return "dormrepair_list";
    }


    //    excel
    @RequestMapping(value = "/newexportdormrepairlist")
    public void exportlist(HttpServletRequest request, HttpServletResponse response)throws IOException {
        List<DormRepair> dormRepairList = dormRepairService.getAll();
        //创建一个工作表
        HSSFWorkbook workbook = new HSSFWorkbook();
        //创建标签页
        HSSFSheet sheet = workbook.createSheet("宿舍维修信息");
        //创建标题行
        HSSFRow row = sheet.createRow(0);
        //在标题行中设置单元格
        row.createCell(0).setCellValue("编号");
        row.createCell(1).setCellValue("宿舍号");
        row.createCell(2).setCellValue("宿舍楼");
        row.createCell(3).setCellValue("维修人员");
        row.createCell(4).setCellValue("报修物品");
        row.createCell(5).setCellValue("保修时间");
        row.createCell(6).setCellValue("更新时间");
        //生成数据行，遍历集合，每一个对象对应一行
        for(int i =0 ;i < dormRepairList.size() ;i++){
            //有多少个用户就得生成几行
            row = sheet.createRow(sheet.getLastRowNum() +1);
            //行创建了添加数据
            row.createCell(0).setCellValue(dormRepairList.get(i).getR_id());
            row.createCell(1).setCellValue(dormRepairList.get(i).getD_id());
            row.createCell(2).setCellValue(dormRepairList.get(i).getD_dormbuilding());
            row.createCell(3).setCellValue(dormRepairList.get(i).getR_name());
            row.createCell(4).setCellValue(dormRepairList.get(i).getReason());
            row.createCell(5).setCellValue(dormRepairList.get(i).getCreate_time());
            row.createCell(6).setCellValue(dormRepairList.get(i).getUpdate_time());
        }
        //数据生成完毕，下面就要下载了，
        //通过response响应到客户端
        String filename = "宿舍维修信息.xls";
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
    @RequestMapping(value = "/exportdormrepairlist", method = RequestMethod.POST)
    @ResponseBody
    public List<DormRepair> exportDormrepair(){
        List<DormRepair> dormRepairList = dormRepairService.getAll();
        return dormRepairList;
    }


    /**
     * 添加宿舍信息
     */
    @RequestMapping(value = "/addDormRepair" ,method = RequestMethod.POST)
    @ResponseBody
    public String addDormitory( @RequestBody DormRepair dormrepair) {
        int d = dormRepairService.addDormRepair(dormrepair);
        return "dormrepair_list";
    }

    /**
     * 删除宿舍信息
     */
    @RequestMapping( "/deleteDormRepair")
    @ResponseBody
    public String deleteDormRepair(Integer r_id) {
        int d = dormRepairService.deleteDormRepair(r_id);
        return "dormrepair_list";
    }

    /**
     * 修改学生信息
     */
    @RequestMapping( "/updateDormRepair")
    public String updateDormRepair(DormRepair dormrepair) {
        dormrepair.setUpdate_time(new Date());
        int d = dormRepairService.updateDormRepair(dormrepair);
        return "redirect:/findDormRepair";
    }


    @RequestMapping( "/findDormRepairById")
    public String findDormRepairById(Integer r_id, HttpSession session) {
        DormRepair d= dormRepairService.findDormRepairById(r_id);
        session.setAttribute("d",d);
        return "dormrepair_edit";
    }
//    增加一条报修记录
    @RequestMapping( "/addrepair")
    public String addrepair(Integer d_id,String d_dormbuilding,String reason,Model model) {
        model.addAttribute("msg","报修失败，请联系宿舍管理员");
        DormRepair dormRepair = new DormRepair();
        dormRepair.setD_id(d_id);
        dormRepair.setD_dormbuilding(d_dormbuilding);
        dormRepair.setReason(reason);
        dormRepair.setCreate_time(new Date());
        int nums = dormRepairService.addrepair(dormRepair);
        if (nums > 0){
            model.addAttribute("msg","报修成功，请等待维修人员上门修理");
        }
    return "info1";
}
}
