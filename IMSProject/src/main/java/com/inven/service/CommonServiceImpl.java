package com.inven.service;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;

import com.inven.common.CommonUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import com.inven.service.inter.CommonService;
import org.springframework.util.FileCopyUtils;

@Slf4j
@Service("commonService")
public class CommonServiceImpl implements CommonService {

    DefaultResourceLoader drl = new DefaultResourceLoader();
    Resource resource = drl.getResource("classpath:static/resources/pass/pwd.txt");

    //로그인 용 비밀번호 체크
    public boolean loginCheck(Map<String, Object> map) {
        try{
            String txt = new String(FileCopyUtils.copyToByteArray(resource.getInputStream()), StandardCharsets.UTF_8);
            log.debug(txt);
            String pwd = CommonUtils.getEncrypt(map.get("pwd").toString(), "cloth");
            return txt.equals(pwd);
        } catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

    //비밀번호 변경
    public boolean loginChange(Map<String, Object> map){

        String newPwd = map.get("newPwd").toString();
        String encPwd = CommonUtils.getEncrypt(newPwd, "cloth");

        FileWriter writer = null;
        try {
            File file = new File(resource.getURI());
            // 기존 파일의 내용에 이어서 쓰려면 true를, 기존 내용을 없애고 새로 쓰려면 false를 지정한다.
            writer = new FileWriter(file, false);
            writer.write(encPwd);
            writer.flush();

//            /*********************** FILE READ ***********************/
//            FileReader rw = new FileReader(file);
//            BufferedReader br = new BufferedReader(rw);
//
//            //읽을 라인이 없을 경우 br은 null을 리턴한다.
//            String readLine = null ;
//            System.out.print("File Read : ");
//            while( ( readLine =  br.readLine()) != null ){
//                System.out.println(readLine);
//            }
            return true;
        } catch(IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if(writer != null) writer.close();
            } catch(IOException e) {
                e.printStackTrace();
            }
        }
        return false;
    }
}
