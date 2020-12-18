package com.inven.service;

import java.util.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.inven.mapper.CommonMapper;
import com.inven.service.inter.CommonService;


@Service("commonService")
public class CommonServiceImpl implements CommonService {
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private CommonMapper cmnMapper;
	
	public Map<String, Object> loginCheck(Map<String, Object> map){ return cmnMapper.loginCheck(map); }
}
