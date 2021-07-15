package com.icss.controller;


import com.icss.domain.Admin;
import com.icss.domain.Dormitory;
import com.icss.domain.PageInfo;
import com.icss.service.AdminService;
import com.icss.util.MD5Util;
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
public class AdminController {
    // 依赖注入
    @Autowired
    private AdminService adminService;
    /**
     * 用户登录
     */
    /**
     * 将提交数据(username,password)写入Admin对象
     */
    @RequestMapping(value = "login")
    public String login( Admin admin, Model model, HttpSession session, HttpServletRequest request) {
        // 通过账号和密码查询用户

//		admin.setA_password(MD5Util.MD5EncodeUtf8(admin.getA_password()));
        admin.setA_password(admin.getA_password());
        Admin ad = adminService.findAdmin(admin);
        if(ad!=null){
            session.setAttribute("ad", ad);
            return "homepage";
        }
        model.addAttribute("msg", "用户名或密码错误，请重新登录！");
        return "teacherlogin";
    }

    /**
     * 退出登录
     */
    @RequestMapping(value = "/loginOut")
    public String loginOut(Admin admin, Model model, HttpSession session) {
        session.invalidate();
        return "/teacherlogin";

    }

    /**
     * 分页查询
     */
    @RequestMapping(value = "/findAdmin")
    public String findAdmin(String a_username, String a_describe,Integer pageIndex,
                            Integer a_id ,Integer pageSize, Model model) {

        PageInfo<Admin> ai = adminService.findPageInfo(a_username,a_describe,
                a_id,pageIndex,pageSize);
        model.addAttribute("ai",ai);
        return "admin_list";
    }




    //    excel
    @RequestMapping(value = "/newexportadminlist")
    public void exportlist(HttpServletRequest request, HttpServletResponse response)throws IOException {
        List<Admin> adminList = adminService.getAll();
        //创建一个工作表
        HSSFWorkbook workbook = new HSSFWorkbook();
        //创建标签页
        HSSFSheet sheet = workbook.createSheet("管理员信息");
        //创建标题行
        HSSFRow row = sheet.createRow(0);
        //在标题行中设置单元格
        row.createCell(0).setCellValue("编号");
        row.createCell(1).setCellValue("管理员用户名");
        row.createCell(2).setCellValue("管理员名字");
        row.createCell(3).setCellValue("管理员电话");
        row.createCell(4).setCellValue("级别");

        //生成数据行，遍历集合，每一个对象对应一行
        for(int i =0 ;i < adminList.size() ;i++){
            //有多少个用户就得生成几行
            row = sheet.createRow(sheet.getLastRowNum() +1);
            //行创建了添加数据adminList
            row.createCell(0).setCellValue(adminList.get(i).getA_id());
            row.createCell(1).setCellValue(adminList.get(i).getA_username());
            row.createCell(2).setCellValue(adminList.get(i).getA_name());
            row.createCell(3).setCellValue(adminList.get(i).getA_phone());
            row.createCell(4).setCellValue(adminList.get(i).getA_describe());

        }
        //数据生成完毕，下面就要下载了，
        //通过response响应到客户端
        String filename = "管理员信息.xls";
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
    @RequestMapping(value = "/exportadminlist" , method = RequestMethod.POST)
    @ResponseBody
    public List<Admin> exportAdmin(){
        List<Admin> admin = adminService.getAll();
        return admin;
    }

    /**
     * 添加管理员信息
     */
    @RequestMapping(value = "/addAdmin" ,method = RequestMethod.POST)
    @ResponseBody
    public String addAdmin( @RequestBody Admin admin) {

        //admin.setA_password(MD5Util.MD5EncodeUtf8(admin.getA_password()));
        int a = adminService.addAdmin(admin);
        return "admin_list";
    }

    /**
     * 删除管理员信息；将请求体a_id写入参数a_id
     */
    @RequestMapping( "/deleteAdmin")
    @ResponseBody
    public String deleteAdmin(Integer a_id) {
        int a = adminService.deleteAdmin(a_id);
        return "admin_list";
    }

    /**
     * 修改管理员信息
     */
    /**
     * 将提交数据(a_id,a_username...)写入Admin对象
     */
    @RequestMapping( value = "/updateAdmin", method = RequestMethod.POST)
    public String updateAdmin(Admin admin) {

        admin.setA_password(MD5Util.MD5EncodeUtf8(admin.getA_password()));
        int a = adminService.updateAdmin(admin);
        return "redirect:/findAdmin";
    }


    /**
     * 根据管理员Id搜索;将请求数据a_id写入参数a_id
     */
    @RequestMapping( "/findAdminById")
    public String findAdminById( Integer a_id,HttpSession session) {
        Admin a= adminService.findAdminById(a_id);
        session.setAttribute("a",a);
        return "admin_edit";
    }

}
