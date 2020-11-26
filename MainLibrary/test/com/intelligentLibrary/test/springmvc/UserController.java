package com.intelligentLibrary.test.springmvc;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
/*
@Controller
@Transactional
public class UserController {

	@Resource
	private SessionFactory sessionFactory;
	
	@RequestMapping("/usertest.do")
	public void  testSpringMVC(String username,String password)
	{
		User user=new User(1,password,username);
		Session session=sessionFactory.openSession();
		session.save(user);
	}

	
}	*/