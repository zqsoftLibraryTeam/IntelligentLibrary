package com.intelligentLibrary.dev.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.intelligentLibrary.dev.domain.User;

public class AdminLoginInterceptor implements HandlerInterceptor {

	@Override
	public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		// TODO Auto-generated method stub
	}

	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3)
			throws Exception {
		// TODO Auto-generated method stub
		HttpSession session = arg0.getSession();
		User user = (User) session.getAttribute("currentUser");
		if(!user.getIdentify().equals("1")){
			session.setAttribute("message", "û�й���ԱȨ��");
			arg0.getRequestDispatcher("../jsp/userpanel.jsp").forward(arg0, arg1);
		}
	}

	@Override
	public boolean preHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2) throws Exception {
		arg0.setCharacterEncoding("utf-8");
		//���û��Ƿ��½�����ж�
		if(arg0.getSession().getAttribute("currentUser")== null){
			//���˵��û�лỰ�ģ��û�û�е�¼������ֹ���󲢷��͵���½ҳ��
			arg0.getRequestDispatcher("../jsp/userAction_login.jsp").forward(arg0, arg1);
			return false;
		}
		return true;
	}

}
