package com.inven.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

import com.inven.common.CommonUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import com.inven.mapper.CommonMapper;
import com.inven.service.inter.CommonService;

@Slf4j
@Service("commonService")
public class CommonServiceImpl implements CommonService {

    public boolean loginCheck(Map<String, Object> map) throws IOException {
        DefaultResourceLoader drl = new DefaultResourceLoader();
        Resource resource = drl.getResource("classpath:static/resources/pass/pwd.txt");
        log.error(String.valueOf(resource.getURI()));
        String txt = Files.readString(Path.of(resource.getURI()));
        String pwd = CommonUtils.getEncrypt(map.get("pwd").toString(), "cloth");

    	return txt.equals(pwd);
    }
}
