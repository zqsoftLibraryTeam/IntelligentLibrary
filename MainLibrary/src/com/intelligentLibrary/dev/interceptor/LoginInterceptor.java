package com.intelligentLibrary.dev.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class LoginInterceptor implements HandlerInterceptor {

	@Override
	public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
	}

	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3)
			throws Exception {
	}

	@Override
	public boolean preHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2) throws Exception {
		arg0.setCharacterEncoding("utf-8");
		//对用户是否登陆进行判断
		if(arg0.getSession().getAttribute("currentUser")== null){
			//如果说是没有会话的，用户没有登录，就终止请求并发送到登陆页面
			arg0.getRequestDispatcher("../jsp/userAction_login.jsp").forward(arg0, arg1);
			return false;
		}
		return true;
	}

}
