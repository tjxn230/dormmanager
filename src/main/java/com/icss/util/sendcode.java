package com.icss.util;

import com.icss.domain.Code;
import com.icss.service.CodeService;
import com.icss.util.HttpUtils;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class sendcode {

//    注入redis
//      @Autowired
//    private RedisTemplateUtil redisTemplateUtil;
//    private  RedisTemplate redisTemplate;
    @Autowired
    private CodeService codeService;
    public String send(String phone) {
//        String host = "https://dfsns.market.alicloudapi.com";
//        String path = "/data/send_sms";
//        String method = "POST";
//        String appcode = "000446e535e9410ea947348e2e9e38a6";
        String code = "";
        for (int i = 0; i < 6; i++) {
            code = code + (int)(Math.random() * 9);
        }
//        Map<String, String> headers = new HashMap<String, String>();
//        //最后在header中的格式(中间是英文空格)为Authorization:APPCODE 83359fd73fe94948385f570e3c139105
//        headers.put("Authorization", "APPCODE " + appcode);
//        //根据API的要求，定义相对应的Content-Type
//        headers.put("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
//        Map<String, String> querys = new HashMap<String, String>();
//        Map<String, String> bodys = new HashMap<String, String>();
//        bodys.put("content", "code:"+code+",expire_at:5");
//        bodys.put("phone_number", phone);
//        bodys.put("template_id", "TPL_0001");


//        redis存入功能
//        存入redis，保存5分钟
//        redisTemplateUtil.setH(phone,code,60*1);
//        ValueOperations forValue = redisTemplate.opsForValue();
//        forValue.set(phone, code,60*1,TimeUnit.SECONDS);
        System.out.println(code);
        System.out.println(phone);


        //        利用mysql来验证code信息
        Code mysqlcode = new Code();
        mysqlcode.setCode(code);
        mysqlcode.setPhone(phone);
        int nums = codeService.insertCode(mysqlcode);

//        try {
//            /**
//             * 重要提示如下:
//             * HttpUtils请从
//             * https://github.com/aliyun/api-gateway-demo-sign-java/blob/master/src/main/java/com/aliyun/api/gateway/demo/util/HttpUtils.java
//             * 下载
//             *
//             * 相应的依赖请参照
//             * https://github.com/aliyun/api-gateway-demo-sign-java/blob/master/pom.xml
//             */
//            HttpResponse response = HttpUtils.doPost(host, path, method, headers, querys, bodys);
//            System.out.println(response.toString());
//            //获取response的body
//            System.out.println(EntityUtils.toString(response.getEntity()));
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

        return "success";
    }
}