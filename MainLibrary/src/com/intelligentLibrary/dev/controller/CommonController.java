package com.intelligentLibrary.dev.controller;

import java.lang.reflect.Type;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.intelligentLibrary.dev.domain.Book;
import com.intelligentLibrary.dev.domain.User;
import com.intelligentLibrary.dev.service.CommonService;
import com.intelligentLibrary.dev.service.LoginService;
import com.intelligentLibrary.dev.service.UserService;
import com.intelligentLibrary.dev.utils.AllBookTypeAdapter;

/**
 * һЩδ��¼�û��Ļ����Ĳ���
 * @author Sean
 *
 */
@Controller
@Scope("prototype")
@RequestMapping("/common")
public class CommonController {
	
	@Resource
	private LoginService loginService;
	@Resource
	private UserService userService;
	@Resource
	private CommonService commonService;
	/**
	 * δ��¼�û���¼����
	 * @param user
	 * @param session
	 * @return
	 */
	@RequestMapping(value="/login.do")
	public String adminLogin(User user,HttpSession session){
		User daouser = loginService.Login(user);
		if(daouser!=null){
			session.setAttribute("currentUser", daouser);
			return "redirect:/user/borrowinfo.do";
		}
		session.setAttribute("message", "�û������������");
		return "userAction_login";
	}
	
	/**
	 * �����ҳ�����ϼܷ���
	 * @return
	 */
	@RequestMapping(value="/getindexnewbook.do",method=RequestMethod.GET)
	public @ResponseBody String indexNewBook(){
		int num = commonService.getBookAllNum();
		Type objtype = new TypeToken<List<Book>>(){}.getType();
		Gson booksgson = new GsonBuilder().registerTypeAdapter(objtype, new AllBookTypeAdapter()).create();
		List<Book> books = commonService.getsubBook(num);
		String jsonobj = booksgson.toJson(books,objtype);
		return jsonobj;
	}
	/**
	 * �����ҳ�����������鱾
	 * @param num  ��Ҫ��ȡ����
	 * @return �鱾����
	 */
	@RequestMapping(value="/getindextopbook.do")
	public @ResponseBody String indexTopBook(@RequestParam("num")int num){
		Type objtype = new TypeToken<List<Book>>(){}.getType();
		Gson booksgson = new GsonBuilder().registerTypeAdapter(objtype, new AllBookTypeAdapter()).create();
		String jsonobj = booksgson.toJson(commonService.getHotBooks(num),objtype);
		return jsonobj;
	}
	
	/**
	 * ��ѯ�鱾
	 * @param request 
	 * @return �����鱾��ѯ����
	 */
	@RequestMapping("/findbook.do")
	public ModelAndView findBook(HttpServletRequest request) {
		String bywhat = request.getParameter("bywhat");
		String thevalue = request.getParameter("thevalue");
		request.setAttribute("booklist", userService.findBookBywhat(bywhat, thevalue));
		return new ModelAndView("userAction/findBookandborrow");
	}
	
	/**
	 * ��׿�ϵķ���,������������
	 * @param bywhat
	 * @param thevalue
	 * @return
	 */
	@RequestMapping(value="/findbookAndroid.do",method=RequestMethod.GET)
	public @ResponseBody String findBookAndroid(@RequestParam String bywhat,@RequestParam String thevalue){
		Type objtype = new TypeToken<List<Book>>(){}.getType();
		Gson booksgson = new GsonBuilder().registerTypeAdapter(objtype, new AllBookTypeAdapter()).create();
		List<Book> books = userService.findBookBywhat(bywhat, thevalue);
		String jsonobj = booksgson.toJson(books,objtype);
		return jsonobj;
	}
	
}
