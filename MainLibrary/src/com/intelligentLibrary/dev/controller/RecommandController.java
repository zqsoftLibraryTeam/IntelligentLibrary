package com.intelligentLibrary.dev.controller;


import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.intelligentLibrary.dev.Dao.AdminManageUserDAO;
import com.intelligentLibrary.dev.domain.User;
import com.intelligentLibrary.dev.service.RecommandService;
import com.intelligentLibrary.recommand.corearithmetic.AprioriArithmetic;
import com.intelligentLibrary.recommand.corearithmetic.ReadIntoText;

@Controller
@Scope("prototype")
@RequestMapping(value="/recommand")
public class RecommandController {
	
	@Resource
	RecommandService recommandService;
	@Resource
	ReadIntoText readIntoText;
	@Resource
	AprioriArithmetic aprioriArithmetic;
	@Resource
	AdminManageUserDAO adminManageUserDAO;
	/**
	 * 姝ゆ柟娉曚负锛屽綋鐢ㄦ埛鎯宠杩涜鎺ㄨ崘鏃舵墍杩涜鐨勬帹鑽�
	 * @param input
	 * @param userid 鐢ㄦ埛鐨刬d,浠巗ession涓幏鍙�,涓�鏍峰湪浼犻�掑埌姝ゆ柟娉曞墠瑕佸厛鍘诲垽瀹歴ession涓殑user鏄惁涓虹┖
	 * @return
	 */
	@RequestMapping(value="/recommand.do")
	public ModelAndView bookstorecommand(@RequestParam String input,@RequestParam int userid)
	{
		ModelAndView mv=new ModelAndView();
		if("illegalrequest".equals(recommandService.requesthandle(input, userid)))
		{
			//杩斿洖璇ラ〉闈㈠苟鎻愮ず閿欒
			mv.setViewName("");
			mv.addObject("errorMessage", "璇锋眰閿欒");
		}
		else{
			
		}
		return mv;
	}
	
	@RequestMapping(value="/testRecommand.do")
	@ResponseBody
	public void testRecommand() throws IOException
	{
		readIntoText.writeIntoFile();
		aprioriArithmetic.MakingRules();
		//鐢熸垚鎺ㄨ崘
		for(User user:adminManageUserDAO.findAll())
		{
			recommandService.writeintorecommand(user);
		}
		//鐢熸垚瑙勫垯
		/*Map<String,String> rules= aprioriArithmetic.MakingRules();
		Iterator<Map.Entry<String,String>> entries=rules.entrySet().iterator();
		while(entries.hasNext())
		{
			Map.Entry<String, String> entry=entries.next();
			System.out.println(entry.getKey());
		}
		System.out.println("------------------");*/
	}
	
}
