package com.intelligentLibrary.test.spring;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.intelligentLibrary.dev.domain.Book;


//import com.intelligentLibrary.test.hibernate.TestService;

public class SpringTest {
		
	private ApplicationContext ctx=new ClassPathXmlApplicationContext("applicationContext-spring.xml");
	private Log log =LogFactory.getLog(getClass());
	
	//测试SessionFactory
	@Test
	public void testSessionFactory() {
		SessionFactory sf=(SessionFactory) ctx.getBean("sessionFactory");
		Session session=sf.openSession();
		List<Book> books=session.createQuery("from Book").list();
		System.out.println(books.size());
	}
	
	@Test
	public void test2()
	{
		int a=1;
		short b=(short)1;
		if(a==b)
		{
			System.out.println("����");
		}
	}
}
	/*
	//测试事务
	@Test
	public void testTranscation()
	{
		//TestService testService=ctx.getBean(TestService.class);
		//testService.save();
	}
	
	@Test
	public void testLog()
	{
		log.debug("这是debug信息");
		log.info("这是info信息");
		log.warn("这是warn信息");
		log.fatal("这是fatal信息");
		log.error("这是error信息");
		
			
	}
}*/
