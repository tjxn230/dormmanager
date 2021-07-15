package com.icss.controller;

import com.icss.domain.*;
import com.icss.service.CodeService;
import com.icss.service.StudentCleanService;
import com.icss.service.StudentService;
import com.icss.util.HttpUtils;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * 用户控制器类
 */
@Controller
public class StudentController {
    // 依赖注入
    @Autowired
    private StudentService studentService;

    @Autowired
    private CodeService codeService;

    @Autowired
    private StudentCleanService studentCleanService;



//    redis
    @Autowired
    private RedisTemplate redisTemplate;

    /**
     *管理员端controller
     */

    /**
     * 分页查询
     * pageIndex 当前页码
     * pageSize  显示条数
     */
    @RequestMapping(value = "/findStudent")
    public String findStudent(String s_name, Integer s_studentid, Integer s_classid, String s_classname,
                              Integer pageIndex, Integer pageSize, Model model) {

        PageInfo<Student> pi = studentService.findPageInfo(s_name, s_studentid, s_classid,
                s_classname, pageIndex, pageSize);
        model.addAttribute("pi", pi);
        model.addAttribute("s_name", s_name);
        return "student_list";
    }


//新导出excel
    @RequestMapping(value = "/newexportstudentlist")
    public void exportstudentlist(HttpServletRequest request, HttpServletResponse response)throws IOException {
        List<Student> studentList = studentService.getAll();
        //创建一个工作表
        HSSFWorkbook workbook = new HSSFWorkbook();
        //创建标签页
        HSSFSheet sheet = workbook.createSheet("学生信息");
        //创建标题行
        HSSFRow row = sheet.createRow(0);
        //在标题行中设置单元格
        row.createCell(0).setCellValue("编号");
        row.createCell(1).setCellValue("学生学号");
        row.createCell(2).setCellValue("学生姓名");
        row.createCell(3).setCellValue("学生性别");
        row.createCell(4).setCellValue("学生年龄");
        row.createCell(5).setCellValue("学生电话");
        row.createCell(6).setCellValue("学生班级Id");
        row.createCell(7).setCellValue("学生班级名字");
        row.createCell(8).setCellValue("学生宿舍号码");
        //生成数据行，遍历集合，每一个对象对应一行
        for(int i =0 ;i < studentList.size() ;i++){
            //有多少个用户就得生成几行
            row = sheet.createRow(sheet.getLastRowNum() +1);
            //行创建了添加数据
            row.createCell(0).setCellValue(studentList.get(i).getS_id());
            row.createCell(1).setCellValue(studentList.get(i).getS_studentid());
            row.createCell(2).setCellValue(studentList.get(i).getS_name());
            row.createCell(3).setCellValue(studentList.get(i).getS_sex());
            row.createCell(4).setCellValue(studentList.get(i).getS_age());
            row.createCell(5).setCellValue(studentList.get(i).getS_phone());
            row.createCell(6).setCellValue(studentList.get(i).getS_classid());
            row.createCell(7).setCellValue(studentList.get(i).getS_classname());
            row.createCell(8).setCellValue(studentList.get(i).getS_dormitoryid());
        }
        //数据生成完毕，下面就要下载了，
        //通过response响应到客户端
        String filename = "学生信息.xls";
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
    @RequestMapping(value = "/exportstudentlist", method = RequestMethod.POST)
    @ResponseBody
    public List<Student> exportStudent() {
        List<Student> studentList = studentService.getAll();
        return studentList;
    }

    /**
     * 删除学生信息
     */
    @RequestMapping("/deleteStudent")
    @ResponseBody
    public String deleteStudent(Integer s_id) {
        int s = studentService.deleteStudent(s_id);
        return "student_list";
    }

    /**
     * 添加学生信息
     */

    @RequestMapping(value = "/addStudent", method = RequestMethod.POST)
    @ResponseBody
    public String addStudent(@RequestBody Student student) {
        int s = studentService.addStudent(student);
        return "student_list";
    }

    /**
     * 修改学生信息
     */
    @RequestMapping(value = "/updateStudent", method = RequestMethod.POST)
    public String updateStudent(Student student) {
        int s = studentService.updateStudent(student);
        return "redirect:/findStudent";
    }


    @RequestMapping("/findStudentById")
    public String findStudentById(Integer s_id, HttpSession session) {

        Student s = studentService.findStudentById(s_id);
        session.setAttribute("s", s);
        return "student_edit";
    }


//    跳转页面
    @RequestMapping("/golist")
    public String returnpage() {
        return "studentaccount_list";
    }

    //    查询所有学生卡
    @RequestMapping("/findStudentAccount")
    @ResponseBody
    public GridBean findStudentAccount(Model model) {
        List<StudentAccount> studentAccounts = studentService.findStudentAccount();
        return  new GridBean(0,"学生账户信息",studentAccounts.size(),studentAccounts);
    }

    /**
     * 学生端controller
     */
    @RequestMapping("/stulogin")
    public String stulogin(String phone, String password, HttpSession session, Model model) {
        StudentAccount sa = studentService.studentlogin(phone, password);
        if (sa != null) {
            StudentCard sc = studentService.findstudentcardbysid(sa.getS_studentid());
            session.setAttribute("sc", sc);
            session.setAttribute("sa", sa);
            return "student_index";
        }
        model.addAttribute("msg", "学生密码或用户名输入错误，请重新输入！");
        return "studentlogin";
    }



    //    学生卡查询
    @RequestMapping("/stucard")
    public String findstudentcardbysid(Integer id, Model model) {
        StudentCard studentCard = studentService.findstudentcardbysid(id);
        model.addAttribute("studentCard", studentCard);
        return "studentcard";
    }

    //    通过id查学生卡
    @RequestMapping("/selectdebtbyid")
    public String selectdebtbyid(Integer id, Model model) {
        StudentAccount studentAccount = studentService.selectdebtbyid(id);
        model.addAttribute("studentAccount", studentAccount);
        return "paymoney";
    }

    //    充值账户
    @RequestMapping("/updateaccount")
    public String updateaccount(String s_phone, Double s_account, String s_payway, Model model) {
        int nums = studentService.updateaccount(s_phone, s_account, s_payway);
        if (nums>0) {
            model.addAttribute("msg","充值成功！！向卡里充值了"+s_account+"元");
        }
        return "info";
    }

    //    缴费
    @RequestMapping("/paymoney")
    public String updateaccount(Integer stuid, Double account,Double debt, Model model) {
        if(debt == 0) {
            model.addAttribute("msg", "该学生没有欠款");
        }if(account<debt){
            model.addAttribute("msg", "该学生账户上没有足够的金额，请充值后再交费");
        }
        if ( debt>0 && account >= debt) {
            int nums = studentService.paymoney(stuid, account, debt);
            if (nums > 0) {
                model.addAttribute("msg", "缴费成功！！当前账户还剩余" + (account - debt )+ "元,"+"本学期欠款已还清");
            }
        }
        return "info";
    }

//    学生注册发送验证码
    @RequestMapping(value = "/send")
        @ResponseBody
        public String send(String phone) {
//        无法注入redistemp的方法，未知原因
//            sendcode sendcode = new sendcode();
//            String jieguo = sendcode.send(phone);
//            return jieguo;
//阿里云发送验证码的接口
         String host = "https://dfsns.market.alicloudapi.com";
         String path = "/data/send_sms";
         String method = "POST";
         String appcode = "000446e535e9410ea947348e2e9e38a6";

        String code = "";
        for (int i = 0; i < 6; i++) {
            code = code + (int)(Math.random() * 9);
        }
        System.out.println("phone="+phone);
        System.out.println("code="+code);


        Map<String, String> headers = new HashMap<String, String>();
      //最后在header中的格式(中间是英文空格)为Authorization:APPCODE 83359fd73fe94948385f570e3c139105
       headers.put("Authorization", "APPCODE " + appcode);
       //根据API的要求，定义相对应的Content-Type
        headers.put("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
        Map<String, String> querys = new HashMap<String, String>();
        Map<String, String> bodys = new HashMap<String, String>();
        bodys.put("content", "code:"+code+",expire_at:5");
        bodys.put("phone_number", phone);
        bodys.put("template_id", "TPL_0001");


//        redis存入功能
//        存入redis，保存5分钟
       ValueOperations forValue = redisTemplate.opsForValue();
       forValue.set(phone, code,60*5, TimeUnit.SECONDS);

//        //        利用mysql来验证code信息
//        Code mysqlcode = new Code();
//        mysqlcode.setCode(code);
//        mysqlcode.setPhone(phone);
//        int nums = codeService.insertCode(mysqlcode);
      try {
            /**
             * 重要提示如下:
            * HttpUtils请从
            * https://github.com/aliyun/api-gateway-demo-sign-java/blob/master/src/main/java/com/aliyun/api/gateway/demo/util/HttpUtils.java
            * 下载
            * 相应的依赖请参照
            */
           HttpResponse response = HttpUtils.doPost(host, path, method, headers, querys, bodys);
            System.out.println(response.toString());
           //获取response的body
            System.out.println(EntityUtils.toString(response.getEntity()));
       } catch (Exception e) {
            e.printStackTrace();
        }
        return "success";
    }

//学生注册上后添加学生到学生表register
    @RequestMapping(value = "/registerstudent")
    public String register(Integer s_studentid,String s_name,String s_sex,Integer s_age,String s_phone,Integer s_classid,String s_classname,String s_password,String yanzhengma,Model model) {
//mysql检测验证码
//        Code mycode = codeService.findCode(s_phone);
//        String code = mycode.getCode();
//        System.out.println("code="+code);
//redis检测验证码
        ValueOperations forValue = redisTemplate.opsForValue();
        String code = (String) forValue.get(s_phone);
//      血的教训 if (code == yanzhengma)
//      改了一个小时才发现是字符串，需要用equals比较
        if (yanzhengma.equals(code)){
        //        创建学生实体类存入
            Student student = new Student();
            student.setS_studentid(s_studentid);
            student.setS_name(s_name);
            student.setS_sex(s_sex);
            student.setS_age(s_age);
            student.setS_phone(s_phone);
            student.setS_dormitoryid(0);
            student.setS_classid(s_classid);
            student.setS_classname(s_classname);
//            创建学生账户实体类
            StudentAccount studentAccount = new StudentAccount();
            studentAccount.setS_studentid(s_studentid);
            studentAccount.setS_password(s_password);
            studentAccount.setS_phone(s_phone);
            studentAccount.setS_name(s_name);
            studentAccount.setS_account(0.0);
            studentAccount.setS_debt(14000.0);
            studentAccount.setS_payway("支付宝");
//        创建卫生等级实体类
            StudentClean studentClean = new StudentClean();
            studentClean.setS_studentid(s_studentid);
            studentClean.setS_name(s_name);
            studentClean.setS_grade(6);
            studentClean.setS_classid(s_classid);
            studentClean.setCreate_time(new Date());
            studentClean.setUpdate_time(new Date());
//        添加一个学生需要添加3个表，stgrade表，s_student表,d_student表
//        添加d_student表
            int nums = studentService.addStudent(student);
            int nums1 = studentService.addAccount(studentAccount);
            int nums2 = studentCleanService.addStudentClean(studentClean);
            model.addAttribute("msg","注册成功，请登录");
        }else {
            model.addAttribute("msg","验证码输入错误，请重新注册");
        }
        return "studentlogin";
    }


// 换宿舍主页
    @RequestMapping("/choosedorm")
    public String choosedorm(Integer id,Model model) {
        StudentCard studentCard = studentService.findstudentcardbysid(id);
        model.addAttribute("studentCard", studentCard);
        String s1 ="0";
        if (s1.equals(studentCard.getS_dormitoryid())){
            model.addAttribute("msg","该学生暂时还没有宿舍，请申请一个宿舍");
        }else {
            model.addAttribute("msg","该学生的宿舍是："+studentCard.getS_dormitoryid()+",如果需要换宿,请点击选择宿舍按钮");
        }
        return "exchangedorm";
    }

//    修改密码
    @RequestMapping("/password")
    public String updatepassword(Integer s_studentid,String s_password){
        int nums = studentService.updatepassword(s_studentid, s_password);
        return "studentaccount_list";
    }

    //    修改欠款
    @RequestMapping("/debt")
    public String updatedebt(Integer s_studentid,Double s_debt){
        int nums = studentService.updatedebt(s_studentid, s_debt);
        return "studentaccount_list";
    }
}

